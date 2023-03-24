# telex
An international system of telegraphy with printed messages transmitted and received by teleprinters using the public telecommunications network.


# Mongo DB key file generation
Create a key file using the following command:

openssl rand -base64 756 > mongodb-keyfile

chmod 400 mongodb-keyfile
chown 999:999 /data/mongodb-keyfile

./db_start.sh

setup cluster
#
docker exec -ti mongo1 bash
#
mongosh -u uber -p uber123 --authenticationDatabase admin
#
use admin
#
rs.initiate()
#
conf = rs.conf();
#
rs.add("mongo2");
#
rs.add("mongo3");
#
rs.reconfig(conf);
#
rs.status()
#

./run.sh
#

# HAProxy stats

http://127.0.0.1:8404/

# HAProxy Request

http://localhost:8405/telex/api/send?telex=85


curl -X 'POST' \
  'http://localhost:8405/telex/api/send' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "armen",
  "age": 45
}'

curl -X 'GET' \
  'http://localhost:8405/telex/api/geohash?lat=40.177200&lon=44.503490' \
  -H 'accept: */*'


curl -X 'GET' \
  'http://localhost:8405/telex/api/location?geohash=szpssdn3nk' \
  -H 'accept: */*'

curl -X 'GET' \
  'http://localhost:8405/telex/api/geohash/check?lat=40.177200&lon=44.503490&geohash=szpssdn3nk' \
  -H 'accept: */*'


# Report
http://localhost:2025/startup-report



