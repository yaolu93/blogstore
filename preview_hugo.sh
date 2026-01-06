#!/bin/bash

# --- 配置区 ---
# 获取当前局域网 IP (Debian/Ubuntu 常用命令)
LOCAL_IP=$(hostname -I | awk '{print $1}')
PORT=1313

# --- 逻辑区 ---
echo "------------------------------------------------"
echo "🚀 正在启动 Hugo 预览服务..."
echo "📂 项目路径: $(pwd)"

# 1. 检查端口是否被占用
if lsof -Pi :$PORT -sTCP:LISTEN -t >/dev/null ; then
    echo "⚠️  警告: 端口 $PORT 已被占用。"
    echo "🔍 尝试清理旧进程..."
    fuser -k $PORT/tcp
    sleep 1
fi

# 2. 打印访问信息
echo "✅ 服务即将就绪："
echo "🔗 本地访问: http://localhost:$PORT"
echo "📱 局域网访问: http://$LOCAL_IP:$PORT"
echo "------------------------------------------------"
echo "💡 提示: 按 CTRL+C 可以停止预览并退出"

# 3. 运行 Hugo Server # -D: 包含草稿 (Drafts) # -E: 包含过期内容 (Expired)
# -F: 包含未来发布内容 (Future)
# --bind: 绑定到 0.0.0.0 允许外部访问
# --gc: 构建后清理缓存
hugo server -D -E -F --bind 0.0.0.0 --baseURL http://$LOCAL_IP --port $PORT --gc
