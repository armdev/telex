#!/bin/bash


if [ $# -lt 1 ]
then
        echo "Usage : ./killport.sh portnumber"
        exit
fi

sudo kill -9 $(sudo lsof -t -i:$1)

