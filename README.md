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


# Report
http://localhost:2025/startup-report

