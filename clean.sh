#!/usr/bin/env bash

docker rm -f $(docker ps -a -q)
docker rmi $(docker images | grep "^<none>" | awk "{print $3}")
docker ps -a
