# telex
An international system of telegraphy with printed messages transmitted and received by teleprinters using the public telecommunications network.

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

