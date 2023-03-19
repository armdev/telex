#!/usr/bin/env bash

if [ $# -lt 1 ]
then
        echo "Usage : ./push.sh Type your push message! What is your task??"
        exit
fi

git pull

git add .

git commit -m "$1"

git push






