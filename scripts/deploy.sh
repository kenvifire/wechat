#!/usr/bin/env bash

DEPLOY_USER=$1
ENV=$2
PROJECT=$3

cd /usr/${DEPLOY_USER}/${PROJECT}/${ENV}
nohup java -jar itluobo-wechat-0.1.0.jar > service.log &
echo "start service success"
echo "travis build finished"
