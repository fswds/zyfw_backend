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
echo "  =====关闭Java应用======"
PROCESS=`ps -ef |grep $AppName.jar |grep -v grep |awk '{print $2}'`
echo "$PROCESS"
for i in $PROCESS
do
  echo "Kill the $1 process [ $i ]"
  kill -9 $i
done