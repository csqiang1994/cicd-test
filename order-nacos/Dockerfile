# 从aws ecr拉取镜像，以防dockerhub速率限制
FROM 188635254879.dkr.ecr.us-east-1.amazonaws.com/jdk11:latest

ENV JVM_OPTS -Xms600M -Xmx1600M
ENV HOME_PATH /home


ADD target/order-nacos.jar /usr/share/dev-app/order-nacos.jar

RUN rm /bin/sh && ln -s /bin/bash /bin/sh

EXPOSE 8181
CMD  java ${JVM_OPTS} -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/log -jar /usr/share/dev-app/order-nacos.jar
