#!/bin/bash
cd `dirname $0`
# 返回脚本所在到上一级项目根目录路径 bin的上级目录
cd ..
# `pwd` 执行系统命令并获得结果
VAR_DEPLOY_DIR=$(pwd)
# 打印项目根目录绝对路径
echo "项目根目录绝对路径：$VAR_DEPLOY_DIR"
# jar包名称
VAR_JAR_NAME=$(ls $VAR_DEPLOY_DIR/lib)
# 配置文件路径
VAR_CONF_DIR=$VAR_DEPLOY_DIR/config/
# 日志存储路径
VAR_LOG_DIR=$VAR_DEPLOY_DIR/logs/
# jar包全路径
VAR_LIB_DIR=$VAR_DEPLOY_DIR/lib/$VAR_JAR_NAME
# 查看此进程是否存在
VAR_LIB_JARS=`ls $VAR_LIB_DIR|grep .jar|awk '{print "'$VAR_LIB_DIR'/"$0}'|tr "\n" ":"`
# 控制台日志
VAR_STDOUT_FILE=$VAR_LOG_DIR/stdout.log

if [ ! -d $VAR_LOG_DIR ]; then
    mkdir $VAR_LOG_DIR
fi

if [ -n "$JAVA_OPTS" ]; then
    VAR_JAVA_OPTS=$JAVA_OPTS
else
    VAR_JAVA_OPTS="-Xmx1024m -Xms1024m -Xmn512m -XX:MetaspaceSize=128M -XX:MaxMetaspaceSize=128M -Xss256K -XX:+UseConcMarkSweepGC -XX:+UseParNewGC"
fi

#设置系统环境编码（解决因环境编码问题引起的接口中文乱码）
#VAR_JAVA_SYSTEM_ENCODING="-Dfile.encoding=UTF-8"
#设置组件配置参数(可以设置多个以空格分割)
#VAR_ASSEMBLY_CONFIGURATION="--jasypt.encryptor.password=ifly_iptv"


#入口
#MAIN_CLASS="com.tiger.TigerApplication"

VAR_JAVA_PATH=""
if [ -n "$JAVA_HOME" ]; then
    VAR_JAVA_PATH="$JAVA_HOME/bin/java"
else
    VAR_JAVA_PATH="java"
fi

#nohup $VAR_JAVA_PATH $VAR_JAVA_OPTS $VAR_JAVA_SYSTEM_ENCODING -classpath $VAR_CONF_DIR:$VAR_LIB_JARS $VAR_PRE_JAVA_PROPERTIES $MAIN_CLASS > $VAR_STDOUT_FILE 2>&1 &
nohup $VAR_JAVA_PATH $VAR_JAVA_OPTS -jar ${VAR_LIB_DIR} --spring.config.location=$VAR_CONF_DIR --spring.config.additional-location=$VAR_CONF_DIR/application-druid.yml,$VAR_CONF_DIR/application.yml --spring.profiles.active=prod --logging.file.path=$VAR_LOG_DIR > $VAR_STDOUT_FILE 2>&1 &

VAR_PIDS=`ps aux | grep java | grep "$VAR_DEPLOY_DIR" | grep -v grep | grep -v "start.sh" | awk '{print $2}'`
echo "PID: $VAR_PIDS"