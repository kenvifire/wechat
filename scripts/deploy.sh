#!/usr/bin/env bash
set -x

DEPLOY_USER=$1
ENV=$2
PROJECT=$3
PORT=$4

cd /usr/${DEPLOY_USER}/${PROJECT}/${ENV}
cp /usr/${DEPLOY_USER}/${PROJECT}/package/*.jar /usr/${DEPLOY_USER}/${PROJECT}/${ENV}/

##
pid=$(/usr/sbin/lsof -i:${PORT} | grep java | awk '{print $2}')

if [ -z "$pid" ]
then
    echo "no process found"
else
    kill -9 $pid
fi

##
echo $(pwd)
echo "start deploying service"
nohup java  -jar *.jar --server.port=${PORT} > service.log &
sleep 5
echo "start service success"
echo "travis build finished"
