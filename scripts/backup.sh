#!/usr/bin/env bash
set -x

DEPLOY_USER=$1
ENV=$2
PROJECT=$3

cd /usr/${DEPLOY_USER}/${PROJECT}/${ENV}
## check back up folder
if [ ! -d "./back_up" ]; then mkdir back_up
fi

## backup file
temp=$(date +%Y-%m-%d-%H-%M-%s)
filetemp=*.jar
if [ -f $filetemp ]; then mv $filetemp back_up/$temp.jar
fi

