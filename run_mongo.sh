#!/bin/bash

docker compose --file mongo-replicaset.yml --compatibility up -d --build

###docker compose --file mongo-compose.yml --compatibility up -d --build

#sleep 3

docker ps -a

./look.sh mongo1
