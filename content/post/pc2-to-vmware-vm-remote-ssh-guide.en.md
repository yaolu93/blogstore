---
title: "Accessing a VMware NAT Virtual Machine from Another PC: Networking, SSH Authentication, and Troubleshooting"
date: 2026-11-11
lastmod: 2026-11-11
draft: false
tags: ["VMware", "NAT", "SSH", "VSCode", "Windows", "Linux"]
categories: ["Networking"]
author: "Yao Lu"
---

This article records how I connected a second Windows computer to a Linux virtual machine running in VMware, including the port conflict and SSH authentication issues I encountered.

Both computers are connected to the same home Wi-Fi network. PC1 runs VMware Workstation, and PC2 connects to the VM over SSH. Once SSH works, I use VS Code Remote-SSH for remote development.

# Prerequisites

- PC1: Windows with VMware Workstation installed. The Linux VM uses a NAT network.
- PC2: Windows with OpenSSH Client, VS Code, and the Remote - SSH extension installed.
- Linux VM: An SSH server is running, and a Linux user account is available for login.

The IP addresses and ports below come from this experiment. Replace them with the values for your own setup.

# Network Topology

```text
Home Wi-Fi / LAN 192.168.0.0/24
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

> In this setup, VMnet0 is configured as a NAT network. VMware commonly uses VMnet8 for NAT by default. Check Virtual Network Editor to find the network used by your VM.

PC2 is on `192.168.0.0/24`, while the VM is on `172.16.199.0/24`. PC2 has no route to the VM's private subnet, so I use port forwarding on PC1 to reach the VM.

# Check the Network

## PC2 to PC1

First, check the IP addresses on both Windows computers:

```powershell
ipconfig
```

Then test PC1 from PC2:

```powershell
ping 192.168.0.151
arp -a
```

At first, PC2 could not ping PC1. After allowing inbound ICMPv4 requests from PC2 in PC1's firewall, ping succeeded. PC1's MAC address also appeared in the ARP table.

If you encounter the same issue, you can add the following rule in an administrator PowerShell session on PC1:

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

> A failed ping may simply mean that the firewall is blocking ICMP. An ARP entry helps check address resolution on the same subnet, but it does not prove that SSH or HTTP is available.

## PC1 to Linux VM

Test the VM from PC1:

```powershell
ping 172.16.199.201
Test-NetConnection 172.16.199.201 -Port 22
```

In this experiment, PC1 could ping the VM and open `http://172.16.199.201` in a browser. The port 22 test also returned:

```text
TcpTestSucceeded : True
```

Once the VM's network connection and SSH port are reachable from PC1, check the forwarding path from PC2 through PC1.

# VMware NAT Port Forwarding

On PC1, open:

```text
VMware Workstation
  → Edit
  → Virtual Network Editor
  → VMnet0
  → NAT Settings
  → Port Forwarding
```

I used the following port forwarding rules:

| Host Port | Protocol | Virtual Machine IP | VM Port |
| --- | --- | --- | --- |
| `18080` | TCP | `172.16.199.201` | `80` |
| `2223` | TCP | `172.16.199.201` | `22` |

When PC2 connects to port `2223` on PC1, VMware NAT forwards the connection to port `22` on the VM. PC2 does not need to join the VMware private subnet.

## Port Conflict

I initially used host ports `8080` and `2222`. The TCP test from PC2 succeeded on `8080` but failed on `2222`.

Check these ports on PC1:

```powershell
Get-NetTCPConnection -State Listen |
  Where-Object { $_.LocalPort -in 8080,2222 } |
  Select-Object LocalAddress,LocalPort,OwningProcess
```

Port `8080` was occupied by PID `18988`, while nothing was listening on `2222`. I checked the process details:

```powershell
Get-Process -Id 18988 | Select-Object Id,ProcessName,Path
Get-CimInstance Win32_Process -Filter 'ProcessId=18988' |
  Format-List ProcessId,ParentProcessId,CommandLine
```

A separate PowerShell proxy script was listening on `8080`. The earlier TCP test only showed that a program was accepting connections; it did not prove that VMware forwarding was working. I did not establish the exact reason why `2222` had no listener.

You can also check the VMware NAT service and Windows port proxy rules:

```powershell
Get-Service | Where-Object { $_.DisplayName -like '*VMware*NAT*' }
Get-Process vmnat -ErrorAction SilentlyContinue |
  Select-Object Id,ProcessName,Path
netsh interface portproxy show all
```

The NAT service was `Running`, `vmnat.exe` was present, and Windows `portproxy` had no rules. I then changed the forwarding ports to `18080` and `2223` and continued testing.

If the updated rules do not take effect, you can restart the NAT service from an administrator PowerShell session on PC1:

```powershell
Restart-Service -Name 'VMware NAT Service'
```

> Restarting the service briefly interrupts connections for VMs using that NAT network. If a port is occupied, identify the process before deciding whether to stop it.

# Windows Firewall

The following example rules allow only PC2 to access the forwarded ports. Run them in an administrator PowerShell session on PC1:

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

These rules apply to the `Private` network profile. Check the active Windows network profile and existing rules before adding them to avoid duplicates. This connection stays within the home LAN, so no public port forwarding is needed on the home router.

Test from PC2:

```powershell
Test-NetConnection 192.168.0.151 -Port 18080
Test-NetConnection 192.168.0.151 -Port 2223
```

Both ports returned `TcpTestSucceeded : True`. The next step is to check the application itself: whether SSH login succeeds and whether HTTP returns the expected page. In this experiment, I only confirmed TCP connectivity from PC2 to `18080`; I did not separately verify the page content in PC2's browser.

# SSH Authentication

My first login attempt from PC2 used `root`:

```powershell
ssh -p 2223 root@192.168.0.151
```

It returned:

```text
Permission denied (publickey).
```

I then switched to the VM's `yao` user and enabled verbose logging:

```powershell
ssh -vvv -p 2223 yao@192.168.0.151
```

The relevant log lines were:

```text
debug1: Connection established.
debug1: Remote protocol version 2.0, remote software version OpenSSH_9.2p1 Debian-2+deb12u7
debug1: Authentications that can continue: publickey
debug3: no such identity: C:\Users\yao/.ssh/id_rsa: No such file or directory
```

The TCP connection and SSH protocol negotiation had succeeded. The failure occurred during user authentication. The server offered only `publickey` authentication, but PC2 could not find a default private key, and the SSH agent had no usable key.

> The SSH software version helps identify the server software. To confirm that you are connecting to the intended VM, also verify its host key fingerprint and check the server itself.

## Public Key and Private Key

- PC2 stores the user's private key, for example `~/.ssh/id_rsa`.
- The target user on the Linux VM stores the corresponding public key in `~/.ssh/authorized_keys`.
- PC2's `known_hosts` file records server host keys to identify servers.

The final working connection used the `yao` account and `id_rsa`. I did not record every step of preparing those keys. As an alternative, you can generate a separate key pair for PC2:

```powershell
ssh-keygen -t ed25519 -C 'pc2-to-linux-vm'
```

Set a passphrase for the private key. Then, using the VM console or an existing login method, add the generated `.pub` public key to `~/.ssh/authorized_keys` for the VM's `yao` user. Check the directory and file permissions and ownership. Keep the private key on PC2; do not commit it to Git or include it in a blog post.

If you use this new key pair, change `IdentityFile` in the SSH configuration below to `~/.ssh/id_ed25519`.

# VS Code Remote-SSH

Install the [Remote - SSH extension](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-ssh) on PC2, then edit the SSH configuration file:

```text
C:\Users\yao\.ssh\config
```

This was the configuration used for the successful connection:

```sshconfig
Host ydb
    HostName 192.168.0.151
    User yao
    Port 2223
    IdentityFile ~/.ssh/id_rsa
    IdentitiesOnly yes
```

| Setting | Description |
| --- | --- |
| `Host ydb` | Connection alias used on PC2 |
| `HostName` | PC1's LAN IP address |
| `User` | The Linux user on the VM |
| `Port` | The SSH forwarding port on PC1 |
| `IdentityFile` | The private key path on PC2 |
| `IdentitiesOnly yes` | Use only the configured identities for authentication |

First, confirm that you can log in from PC2's terminal:

```powershell
ssh ydb
```

Then open VS Code:

1. Press `Ctrl + Shift + P` to open the Command Palette.
2. Select `Remote-SSH: Connect to Host...`.
3. Select `ydb`. On the first connection, follow the prompts to install and start VS Code Server.
4. Once connected, open a remote terminal and check the environment.

```bash
hostname
whoami
pwd
ip -br addr
```

PC2 can now connect to the Linux VM through `ydb` in VS Code. The editor interface runs on PC2, while the remote terminal and VS Code Server run on the VM.

# Troubleshooting

I checked the connection in order: PC2 to PC1, PC1 to the VM, port forwarding, and SSH authentication. Once one part worked, I moved on to the next.

| Problem | What to Check | Command or Location |
| --- | --- | --- |
| PC2 cannot ping PC1 | Wi-Fi IP addresses and ICMPv4 firewall rules | `ipconfig`, `ping`, `arp -a` |
| PC1 can reach the VM, but PC2 cannot | NAT forwarding and PC1's firewall | Virtual Network Editor |
| TCP test succeeds, but the wrong page appears | Whether another process is using the port | `Get-NetTCPConnection`, `Get-CimInstance` |
| PC1's SSH forwarding port is unreachable | Listening ports, NAT service, and forwarding rules | `Test-NetConnection`, `Get-Service` |
| `Permission denied (publickey)` | Username, client private key, and server authorized public key | `ssh -vvv` |
| SSH works in a terminal, but VS Code fails | Remote-SSH logs, remote server compatibility, and permissions | VS Code Output → Remote-SSH |
| Connection fails after PC1's IP changes | DHCP lease and SSH `HostName` | Router DHCP Reservation |

Keep both PC1's LAN IP and the VM's NAT IP stable. PC2 connects to the first, and the NAT forwarding rules target the second. Update the corresponding configuration whenever either address changes.

# Next Step: Tailscale

This experiment was completed on the same home Wi-Fi network. A possible next step is to connect from an external network using Tailscale. I have not implemented this yet.

| Installation Locations | Connection Path |
| --- | --- |
| PC1 and PC2 | PC2 → PC1's Tailscale IP `:2223` → VMware NAT → VM `:22` |
| Linux VM and PC2 | PC2 → VM's Tailscale IP `:22` |

Neither option requires exposing an SSH port on the home router. Check access policies and host firewalls for the chosen setup. Performance needs to be measured; the number of forwarding steps alone does not determine speed.

# References

- [VMware Workstation Documentation](https://docs.vmware.com/)
- [Microsoft OpenSSH for Windows](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh-overview)
- [OpenSSH Key Management](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh_keymanagement)
- [New-NetFirewallRule](https://learn.microsoft.com/en-us/powershell/module/netsecurity/new-netfirewallrule)
- [VS Code Remote Development using SSH](https://code.visualstudio.com/docs/remote/ssh)
- [Tailscale Documentation](https://tailscale.com/docs/)
