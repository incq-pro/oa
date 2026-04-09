#!/bin/bash

APP_NAME="oa-system-1.0.0.jar"
APP_DIR=$(cd "$(dirname "$0")" && pwd)
JAR_FILE="$APP_DIR/target/$APP_NAME"
LOG_FILE="$APP_DIR/startup.log"
PID_FILE="$APP_DIR/app.pid"

# 默认初始化数据库
INIT_DB="always"

build() {
    echo "正在构建项目..."
    cd "$APP_DIR"
    mvn clean package -DskipTests -q
    if [ $? -eq 0 ]; then
        echo "构建成功: $JAR_FILE"
    else
        echo "构建失败!"
        exit 1
    fi
}

start() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "应用已在运行 (PID: $PID)"
            return
        fi
        rm -f "$PID_FILE"
    fi

    if [ ! -f "$JAR_FILE" ]; then
        echo "JAR 文件不存在，正在构建..."
        build
    fi

    echo "启动应用..."
    if [ "$INIT_DB" = "never" ]; then
        echo "数据库初始化: 跳过"
    else
        echo "数据库初始化: 启用"
    fi

    cd "$APP_DIR"
    nohup java -Dspring.sql.init.mode="$INIT_DB" -jar "$JAR_FILE" > "$LOG_FILE" 2>&1 &
    PID=$!
    echo "$PID" > "$PID_FILE"
    echo "应用已启动 (PID: $PID)"
    echo "日志文件: $LOG_FILE"

    sleep 3
    if ps -p "$PID" > /dev/null 2>&1; then
        echo "应用启动成功!"
    else
        echo "应用启动失败，请查看日志: $LOG_FILE"
        rm -f "$PID_FILE"
    fi
}

stop() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "停止应用 (PID: $PID)..."
            kill "$PID"
            sleep 2
            if ps -p "$PID" > /dev/null 2>&1; then
                kill -9 "$PID"
            fi
            echo "应用已停止"
        else
            echo "应用未运行"
        fi
        rm -f "$PID_FILE"
    else
        echo "未找到PID文件"
    fi
}

status() {
    if [ -f "$PID_FILE" ]; then
        PID=$(cat "$PID_FILE")
        if ps -p "$PID" > /dev/null 2>&1; then
            echo "应用正在运行 (PID: $PID)"
        else
            echo "应用未运行 (PID文件已过期)"
            rm -f "$PID_FILE"
        fi
    else
        echo "应用未运行"
    fi
}

case "$1" in
    build)
        build
        ;;
    start)
        if [ "$2" = "--no-init" ] || [ "$2" = "-n" ]; then
            INIT_DB="never"
        fi
        start
        ;;
    stop)
        stop
        ;;
    restart)
        if [ "$2" = "--no-init" ] || [ "$2" = "-n" ]; then
            INIT_DB="never"
        else
            INIT_DB="always"
        fi
        stop
        sleep 1
        start
        ;;
    status)
        status
        ;;
    *)
        echo "用法: $0 {build|start|stop|restart|status} [选项]"
        echo ""
        echo "选项:"
        echo "  --no-init, -n    启动时不初始化数据库"
        echo ""
        echo "示例:"
        echo "  $0 build                      # 构建项目"
        echo "  $0 start                     # 启动应用，初始化数据库"
        echo "  $0 start --no-init            # 启动应用，跳过数据库初始化"
        echo "  $0 start -n                  # 启动应用，跳过数据库初始化"
        echo "  $0 restart --no-init         # 重启应用，跳过数据库初始化"
        exit 1
        ;;
esac
