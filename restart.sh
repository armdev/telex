#!/bin/bash


if [ $# -lt 1 ]
then
        echo "Usage : ./restart.sh service name"
        exit
fi



#mvn clean install -pl $1  -am -DskipTests=true


docker rm -f $1
docker rmi -f $1
docker-compose -f docker-compose.yml up -d --no-deps --build $1

docker logs --follow $1





