#!/bin/bash


# 遇到错误直接退出
set -e

cd /home/cicd-test/scripts

ls

LOG_FILE="/home/cicd-test/deploy.log"
rm -rf LOG_FILE

echo "currentUser:$(whoami)" >> $LOG_FILE

AWS_REGION=$(jq -r '.[0].region' config.json)
ECR_REPOSITORY_URI=$(jq -r '.[0].repoUri' config.json)
CONTAINER_NAME=$(jq -r '.[0].name' config.json)
export IMAGE_URI=$(jq -r '.[0].imageUri' config.json)

echo "AWS_REGION=$AWS_REGION" >> $LOG_FILE
echo "ECR_REPOSITORY_URI=$ECR_REPOSITORY_URI" >> $LOG_FILE
echo "CONTAINER_NAME=$CONTAINER_NAME" >> $LOG_FILE
echo "IMAGE_URI=$IMAGE_URI" >> $LOG_FILE

# 登录到Amazon ECR
echo "aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPOSITORY_URI}"
aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REPOSITORY_URI} >> $LOG_FILE 2>&1

echo "容器名称: $CONTAINER_NAME"
echo "镜像URI: $IMAGE_URI"

# 拉取最新的镜像
docker pull ${IMAGE_URI} >> $LOG_FILE 2>&1

# 使用docker-compose重启服务
docker-compose down && docker-compose up -d >> $LOG_FILE 2>&1