#!/usr/bin/env bash
set -x

DEPLOY_USER=$1
ENV=$2
PROJECT=$3
PORT=$4

cd /usr/${DEPLOY_USER}/${PROJECT}/${ENV}
cp /usr/${DEPLOY_USER}/${PROJECT}/package/*.jar /usr/${DEPLOY_USER}/${PROJECT}/${ENV}/

##
pid=$(lsof -i:${PORT} | grep java | awk '{print $2}')

if [ -n $pid ]
then kill -9 $pid
fi

##
nohup java -jar *.jar --server.port=${PORT} > service.log &
echo "start service success"
echo "travis build finished"
