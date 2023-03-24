#!/bin/bash

docker-compose -f mongo-compose.yml up -d  --build 

docker logs --follow mongo1





