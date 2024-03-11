FROM adoptopenjdk:11-jdk-hotspot

ENV JVM_OPTS -Xms1600M -Xmx1600M
ENV HOME_PATH /home


ADD target/order-nacos.jar /usr/share/dev-app/order-nacos.jar

RUN rm /bin/sh && ln -s /bin/bash /bin/sh

EXPOSE 8181
CMD  java ${JVM_OPTS} -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/log -jar /usr/share/dev-app/order-nacos.jar
