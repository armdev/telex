# Telex

Telex is an international system of telegraphy that allows for printed messages to be transmitted and received by teleprinters through the public telecommunications network.

## Installation

To run the Telex application, follow these steps:

1. Run the `run_cluster.sh` script once.
2. Run the `run_mongo.sh` script.
3. Run the `run.sh` script.

The application uses HAProxy for load balancing, and you can view the HAProxy stats by visiting `http://127.0.0.1:8404/`.

## Usage

To send a telex message, you can use the following request:


http://localhost:8405/telex/api/send?telex=85


You can also send a telex message using `curl`:

curl -X 'POST'
'http://localhost:8405/telex/api/send'
-H 'accept: /'
-H 'Content-Type: application/json'
-d '{
"name": "armen",
"age": 45
}'



The Telex application also supports geohashing. You can retrieve the geohash for a location by making the following request:

http://localhost:8405/telex/api/geohash?lat=40.177200&lon=44.503490


You can retrieve the location for a geohash by making the following request:

http://localhost:8405/telex/api/location?geohash=szpssdn3nk


You can check if a geohash corresponds to a specific location by making the following request:


http://localhost:8405/telex/api/geohash/check?lat=40.177200&lon=44.503490&geohash=szpssdn3nk

