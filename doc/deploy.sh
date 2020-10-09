#!/bin/bash
PROG_NAME=$0
ACTION=$1
APP_NAME=$2
APP_PORT=$3
APP_START_TIMEOUT=20
HEALTH_CHECK_URL=http://127.0.0.1:${APP_PORT}/actuator/health
APP_HOME=/home/ubuntu/ZhiYi-Cloud
JAR_NAME=${APP_HOME}/package/${APP_NAME}.jar
JAVA_OUT=${APP_HOME}/logs/start.log
NACOS_HOST=127.0.0.1
NACOS_PORT=8848
NACOS_USER=nacos
NACOS_PASS=nacosnacos
usage() {
    echo "Usage: $PROG_NAME {start|stop|restart} {xxx}.jar {port}"
    exit 2
}

health_check() {
    exptime=0
    echo "checking ${HEALTH_CHECK_URL}"
    while true
        do
            status_code=`/usr/bin/curl -X GET -L -o /dev/null --connect-timeout 5 -s -w %{http_code}  ${HEALTH_CHECK_URL}`
            if [ "$?" != "0" ]; then
               echo -n -e "\rapplication not started"
            else
                echo "code is $status_code"
                if [ "$status_code" == "200" ];then
                    break
                fi
            fi
            sleep 1
            ((exptime++))

            echo -e "\rWait app to pass health check: $exptime..."

            if [ $exptime -gt ${APP_START_TIMEOUT} ]; then
                echo 'app start failed'
                exit 1
            fi
        done
    echo "check ${HEALTH_CHECK_URL} success"
}
start_application() {
    echo "starting java process"
    echo ${APP_HOME}
    echo ${JAR_NAME}
    cd ${APP_HOME}
    echo ${NACOS_HOST}
    nohup java -jar -Xms512m -Xmx512m -Xmn256m ${JAR_NAME} --spring.profiles.active=test --spring.cloud.nacos.discovery.password=nacos > ${JAVA_OUT} 2>&1 &
    echo "started java process"
}

stop_application() {
   checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`

   if [[ ! $checkjavapid ]];then
      echo -e "\rno java process"
      return
   fi

   echo "stop java process"
   times=60
   for e in $(seq 60)
   do
        sleep 1
        COSTTIME=$(($times - $e ))
        checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`
        if [[ $checkjavapid ]];then
            kill -9 $checkjavapid
            echo -e  "\r        -- stopping java lasts `expr $COSTTIME` seconds."
        else
            echo -e "\rjava process has exited"
            break;
        fi
   done
   echo ""
}
start() {
    start_application
    health_check
}
stop() {
    stop_application
}
case "$ACTION" in
    start)
        start
    ;;
    stop)
        stop
    ;;
    restart)
        stop
        start
    ;;
    *)
        usage
    ;;
esac
