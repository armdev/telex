#!/bin/bash

DELAY=15

mongosh <<EOF
var config = {
    "_id": "mongo_rs",
    "version": 1,
    "members": [
        {
            "_id": 1,
            "host": "mongo1:27017",
            "priority": 2
        },
        {
            "_id": 2,
            "host": "mongo2:27017",
            "priority": 1
        }
       
    ]
};
rs.initiate(config, { force: true });
EOF

echo "****** Waiting for ${DELAY} seconds for replicaset configuration to be applied ******"

sleep $DELAY
echo "****** Mongo 7  init ******"
mongosh <  /scripts/init.js
