#!/usr/bin/env bash

mvn clean package -U -Dmaven.test.skip=true
#mvn -f dbsetup clean package -U -Dmaven.test.skip=true
docker compose up -d --build
docker logs --follow river

