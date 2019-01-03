#!/usr/bin/env bash
set -x

DEPLOY_USER=$1
ENV=$2
PROJECT=$3
PORT=$4

cd /usr/${DEPLOY_USER}/${PROJECT}/${ENV}


##
nohup java -jar *.jar --server.port=${PORT} > service.log &
echo "start service success"
echo "travis build finished"
