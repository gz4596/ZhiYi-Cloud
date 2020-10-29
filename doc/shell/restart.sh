#!/bin/bash

# mvn -B clean package -Dmaven.test.skip=true
# mkdir -p package
# find . -iname "*.jar" -type f -exec cp {} package \;
# HOME=/home/ubuntu/ZhiYi-Cloud
# mkdir -p ${HOME}
# mkdir -p ${HOME}/logs
# mkdir -p ${HOME}/package
# tar zxvf /tmp/ZhiYI-Cloud.tgz -C ${HOME}/package
# cd $HOME

#/home/staragent/bin/agent.sh restart
#sudo su ubuntu -c 'cd /home/ubuntu/ZhiYi-Cloud/nacos/nacos/bin/ && ./startup.sh'
#sudo su ubuntu -c 'cd /home/ubuntu/ZhiYi-Cloud/frp/frp_linux && ./frpc'

bash deploy.sh restart zhiyi-authorization 6628
bash deploy.sh restart zhiyi-gateway 8008
bash deploy.sh restart zhiyi-monitor 6222
bash deploy.sh restart mall-v1-api-user 5513
bash deploy.sh restart mall-v1-api-admin 6060




