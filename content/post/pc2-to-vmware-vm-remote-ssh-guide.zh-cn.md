---
title: "从另一台计算机访问 VMware NAT 虚拟机：网络连接、SSH 身份验证及故障排除"
date: 2026-11-11
lastmod: 2026-11-11
draft: false
tags: ["VMware", "NAT", "SSH", "VSCode", "Windows", "Linux"]
categories: ["Networking"]
author: "Yao Lu"
---

这篇文章记录我从第二台 Windows 电脑连接 VMware Linux 虚拟机的配置过程，以及中间遇到的端口冲突和 SSH 认证问题。

两台电脑连接同一个家庭 Wi-Fi。PC1 运行 VMware Workstation，PC2 通过 SSH 连接 PC1 中的虚拟机，最后使用 VS Code Remote-SSH 进行远程开发。

# Prerequisites

- PC1：Windows，安装 VMware Workstation，Linux 虚拟机使用 NAT 网络。
- PC2：Windows，安装 OpenSSH Client、VS Code 和 Remote - SSH 扩展。
- Linux VM：SSH 服务已启动，有可登录的 Linux 用户。

下面的 IP 和端口来自这次实验，使用时需要替换为自己的配置。

# Network Topology

```text
家庭 Wi-Fi / LAN 192.168.0.0/24
Router 192.168.0.1
    |
    +-- PC1: Windows 192.168.0.151
    |     |
    |     +-- VMware Workstation / VMnet0 NAT
    |           Host Adapter: 172.16.199.1
    |           NAT Gateway: 172.16.199.254
    |           Linux VM:    172.16.199.201
    |
    +-- PC2: Windows 192.168.0.235
          VS Code + OpenSSH Client

PC2 --> PC1:2223  --> Linux VM:22   (SSH)
PC2 --> PC1:18080 --> Linux VM:80   (HTTP)
```

> 这次实验将 VMnet0 配置为 NAT。VMware 常见的默认 NAT 网络是 VMnet8，实际使用哪个网络，要看 Virtual Network Editor 中的配置。

PC2 和 VM 分别位于 `192.168.0.0/24`、`172.16.199.0/24`。PC2 没有到 VM 私有网段的路由，因此这里使用 PC1 的端口转发访问 VM。

# Check the Network

## PC2 to PC1

先在两台 Windows 电脑上检查 IP：

```powershell
ipconfig
```

然后在 PC2 测试 PC1：

```powershell
ping 192.168.0.151
arp -a
```

最初 PC2 无法 Ping 通 PC1。在 PC1 防火墙中允许来自 PC2 的 ICMPv4 请求后，Ping 成功，ARP 表中也能找到 PC1 的 MAC 地址。

如果遇到同样的问题，可以在 PC1 的管理员 PowerShell 中添加规则：

```powershell
New-NetFirewallRule `
  -DisplayName 'Allow ICMPv4 from PC2' `
  -Direction Inbound `
  -Protocol ICMPv4 `
  -IcmpType 8 `
  -RemoteAddress 192.168.0.235 `
  -Action Allow `
  -Profile Private
```

> Ping 失败可能只是 ICMP 被防火墙拦截。ARP 记录可以辅助检查同一子网中的地址解析，但不能说明 SSH 或 HTTP 服务可用。

## PC1 to Linux VM

在 PC1 测试虚拟机：

```powershell
ping 172.16.199.201
Test-NetConnection 172.16.199.201 -Port 22
```

这次 PC1 可以 Ping 通 VM，浏览器可以打开 `http://172.16.199.201`，22 端口的测试也返回：

```text
TcpTestSucceeded : True
```

PC1 到 VM 的网络和 SSH 端口可达后，再检查 PC2 到 PC1 的转发路径。

# VMware NAT Port Forwarding

在 PC1 打开：

```text
VMware Workstation
  → Edit
  → Virtual Network Editor
  → VMnet0
  → NAT Settings
  → Port Forwarding
```

本次使用的端口转发规则如下：

| Host Port | Protocol | Virtual Machine IP | VM Port |
| --- | --- | --- | --- |
| `18080` | TCP | `172.16.199.201` | `80` |
| `2223` | TCP | `172.16.199.201` | `22` |

PC2 访问 PC1 的 `2223` 端口时，VMware NAT 将连接转发到 VM 的 `22` 端口。PC2 不需要加入 VMware 的私有子网。

## Port Conflict

我最初设置的是 `8080` 和 `2222`。PC2 测试 `8080` 成功，但 `2222` 失败。

在 PC1 检查这两个端口：

```powershell
Get-NetTCPConnection -State Listen |
  Where-Object { $_.LocalPort -in 8080,2222 } |
  Select-Object LocalAddress,LocalPort,OwningProcess
```

结果发现 `8080` 被 PID `18988` 占用，而 `2222` 没有监听。继续检查进程来源：

```powershell
Get-Process -Id 18988 | Select-Object Id,ProcessName,Path
Get-CimInstance Win32_Process -Filter 'ProcessId=18988' |
  Format-List ProcessId,ParentProcessId,CommandLine
```

占用 `8080` 的是另一个 PowerShell 代理脚本。因此，之前的 TCP 测试成功，只说明有程序在响应，不能说明 VMware 转发成功。`2222` 没有监听的具体原因，当时没有进一步确认。

还可以检查 VMware NAT 服务和 Windows 端口代理：

```powershell
Get-Service | Where-Object { $_.DisplayName -like '*VMware*NAT*' }
Get-Process vmnat -ErrorAction SilentlyContinue |
  Select-Object Id,ProcessName,Path
netsh interface portproxy show all
```

这次 NAT 服务处于 `Running` 状态，`vmnat.exe` 存在，Windows `portproxy` 没有规则。最后将转发端口改为 `18080` 和 `2223`，再继续测试。

如果修改规则后没有生效，可以在 PC1 的管理员 PowerShell 中重启 NAT 服务：

```powershell
Restart-Service -Name 'VMware NAT Service'
```

> 重启会短暂中断使用该 NAT 网络的虚拟机连接。发现端口占用时，先确认进程用途，再决定是否停止它。

# Windows Firewall

下面是只允许 PC2 访问转发端口的规则示例。在 PC1 的管理员 PowerShell 中执行：

```powershell
New-NetFirewallRule `
  -DisplayName 'VM HTTP from PC2' `
  -Direction Inbound -Action Allow -Protocol TCP `
  -LocalPort 18080 -RemoteAddress 192.168.0.235 `
  -Profile Private

New-NetFirewallRule `
  -DisplayName 'VM SSH from PC2' `
  -Direction Inbound -Action Allow -Protocol TCP `
  -LocalPort 2223 -RemoteAddress 192.168.0.235 `
  -Profile Private
```

规则使用 `Private` 网络配置文件，添加前先检查 Windows 当前网络配置文件和已有规则，避免重复创建。这次连接只在家庭局域网内进行，不需要配置家庭路由器的公网端口转发。

在 PC2 测试：

```powershell
Test-NetConnection 192.168.0.151 -Port 18080
Test-NetConnection 192.168.0.151 -Port 2223
```

两个端口都返回了 `TcpTestSucceeded : True`。接下来还需要检查应用层：SSH 是否能登录，HTTP 是否能返回预期页面。本次只确认了 PC2 到 `18080` 的 TCP 连通性，没有单独检查 PC2 浏览器中的页面内容。

# SSH Authentication

最初在 PC2 使用 `root` 登录：

```powershell
ssh -p 2223 root@192.168.0.151
```

返回：

```text
Permission denied (publickey).
```

之后改用 VM 中的 `yao` 用户，并打开详细日志：

```powershell
ssh -vvv -p 2223 yao@192.168.0.151
```

日志中的关键信息如下：

```text
debug1: Connection established.
debug1: Remote protocol version 2.0, remote software version OpenSSH_9.2p1 Debian-2+deb12u7
debug1: Authentications that can continue: publickey
debug3: no such identity: C:\Users\yao/.ssh/id_rsa: No such file or directory
```

TCP 连接和 SSH 协议协商已经成功，问题出在用户认证。服务端只提供 `publickey` 认证，PC2 当时没有找到默认私钥，SSH Agent 也没有可用密钥。

> SSH 软件版本只能帮助识别服务端软件。确认连接的是目标 VM，还需要核对服务器主机密钥指纹和服务器端信息。

## Public Key and Private Key

- PC2 保存用户私钥，例如 `~/.ssh/id_rsa`。
- Linux VM 的目标用户在 `~/.ssh/authorized_keys` 中保存对应公钥。
- PC2 的 `known_hosts` 记录服务器主机密钥，用来识别服务器。

这次最终使用 `yao` 用户和 `id_rsa` 登录成功。密钥准备过程没有完整记录，下面补充一种为 PC2 新建密钥的做法：

```powershell
ssh-keygen -t ed25519 -C 'pc2-to-linux-vm'
```

为私钥设置口令，再通过 VM 控制台或已有登录方式，将生成的 `.pub` 公钥添加到 VM 上 `yao` 用户的 `~/.ssh/authorized_keys`，并检查目录、文件权限和所有者。私钥保留在 PC2，不要提交到 Git 或放进博客。

如果使用这对新密钥，下面 SSH 配置中的 `IdentityFile` 应改为 `~/.ssh/id_ed25519`。

# VS Code Remote-SSH

在 PC2 安装 [Remote - SSH 扩展](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-ssh)，然后编辑 SSH 配置文件：

```text
C:\Users\yao\.ssh\config
```

下面是这次连接成功时使用的配置：

```sshconfig
Host ydb
    HostName 192.168.0.151
    User yao
    Port 2223
    IdentityFile ~/.ssh/id_rsa
    IdentitiesOnly yes
```

| 配置项 | 说明 |
| --- | --- |
| `Host ydb` | PC2 上使用的连接别名 |
| `HostName` | PC1 的局域网 IP |
| `User` | VM 中的 Linux 用户 |
| `Port` | PC1 的 SSH 转发端口 |
| `IdentityFile` | PC2 上的私钥路径 |
| `IdentitiesOnly yes` | 只使用配置指定的身份进行认证 |

先在 PC2 终端确认可以登录：

```powershell
ssh ydb
```

再打开 VS Code：

1. 按 `Ctrl + Shift + P` 打开命令面板。
2. 选择 `Remote-SSH: Connect to Host...`。
3. 选择 `ydb`，首次连接时按提示安装和启动 VS Code Server。
4. 连接后打开远程终端，检查当前环境。

```bash
hostname
whoami
pwd
ip -br addr
```

到这里，PC2 已经可以通过 `ydb` 在 VS Code 中连接 Linux VM。编辑器界面运行在 PC2，远程终端和 VS Code Server 运行在 VM 中。

# Troubleshooting

排查时，我按 PC2 到 PC1、PC1 到 VM、端口转发、SSH 认证的顺序检查。前一段路径正常后，再继续往下找。

| 问题 | 检查内容 | 命令或位置 |
| --- | --- | --- |
| PC2 无法 Ping PC1 | Wi-Fi IP、ICMPv4 防火墙规则 | `ipconfig`、`ping`、`arp -a` |
| PC1 能访问 VM，PC2 不能 | NAT 转发和 PC1 防火墙 | Virtual Network Editor |
| TCP 测试成功，页面却不对 | 端口是否被其他程序占用 | `Get-NetTCPConnection`、`Get-CimInstance` |
| PC1 的 SSH 转发端口不可达 | 监听端口、NAT 服务和转发规则 | `Test-NetConnection`、`Get-Service` |
| `Permission denied (publickey)` | 用户名、客户端私钥、服务端公钥 | `ssh -vvv` |
| 终端可以 SSH，VS Code 失败 | Remote-SSH 日志、远程 Server 兼容性和权限 | VS Code Output → Remote-SSH |
| PC1 IP 变化后无法连接 | DHCP 租约和 SSH `HostName` | 路由器 DHCP Reservation |

PC1 的局域网 IP 和 VM 的 NAT IP 都应保持稳定：前者供 PC2 连接，后者是 NAT 转发规则的目标。修改地址后，需要同步更新对应配置。

# Next Step: Tailscale

上面的实验是在同一家庭 Wi-Fi 内完成的。后面可以尝试用 Tailscale 从外部网络连接，目前还没有实施。

| 安装位置 | 连接方式 |
| --- | --- |
| PC1 和 PC2 | PC2 → PC1 的 Tailscale IP `:2223` → VMware NAT → VM `:22` |
| Linux VM 和 PC2 | PC2 → VM 的 Tailscale IP `:22` |

两种方式都不需要在家庭路由器上公开 SSH 端口。选择时还要检查访问策略和主机防火墙，速度则需要实际测试，不能只看转发层数判断。

# References

- [VMware Workstation 文档入口](https://docs.vmware.com/)
- [Microsoft OpenSSH for Windows](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh-overview)
- [OpenSSH 密钥管理](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh_keymanagement)
- [New-NetFirewallRule](https://learn.microsoft.com/en-us/powershell/module/netsecurity/new-netfirewallrule)
- [VS Code Remote Development using SSH](https://code.visualstudio.com/docs/remote/ssh)
- [Tailscale 官方文档](https://tailscale.com/docs/)
