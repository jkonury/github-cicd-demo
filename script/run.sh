#!/bin/sh

JAVA_OPTS="-XX:+UseZGC -XX:MaxRAMPercentage=80.0"

if [ "prod" = "${PROFILE}" ]
then
  JAVA_OPTS="${JAVA_OPTS} -Xmx1024m"
else
  JAVA_OPTS="${JAVA_OPTS}"
fi

IP=$(hostname -i)
POSTFIX=`echo ${IP} | awk -F'.' '{print $3"." $4}'`
APP_NAME=${APP_NAME:-"demo-docker"}
echo "start application profile=${PROFILE} APP_NAME=${APP_NAME}, IP=${IP}, POSTFIX=${POSTFIX}"

if [ "${SCOUTER_ENABLE}" = true ]
then
  java -Djava.security.egd=file:/dev/./urandom \
       -Dspring.profiles.active=${PROFILE} \
       ${JAVA_OPTS} \
       -javaagent:/scouter/scouter.agent.jar \
       -Dscouter.config=/scouter/docker-local.conf \
       -Dobj_host_name=${IP} \
       -Dnet_collector_ip=${SCOUTER_COLLECTOR_IP} \
       -Dobj_name=${APP_NAME}-${POSTFIX} \
       --add-opens=java.base/java.lang=ALL-UNNAMED \
       -Djdk.attach.allowAttachSelf=true \
       org.springframework.boot.loader.launch.JarLauncher
else
  java -Djava.security.egd=file:/dev/./urandom \
       -Dspring.profiles.active=${PROFILE} \
       ${JAVA_OPTS} \
       -javaagent:/scouter/scouter.agent.jar \
       -Dscouter.config=/scouter/docker-local.conf \
       -Dobj_host_name=${IP} \
       -Dnet_collector_ip=${SCOUTER_COLLECTOR_IP} \
       -Dobj_name=${APP_NAME}-${POSTFIX} \
       --add-opens=java.base/java.lang=ALL-UNNAMED \
       -Djdk.attach.allowAttachSelf=true \
       org.springframework.boot.loader.launch.JarLauncher
fi
