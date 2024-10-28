Here’s an enhanced README with a more organized structure, additional explanations, and code formatting for readability:

---

# Telex

**Telex** is an international telegraphy system designed for sending and receiving printed messages over the public telecommunications network. This application simulates the core functionalities of the classic Telex system, integrating message transmission, geohashing capabilities, and location-based services.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
  - [Sending a Telex Message](#sending-a-telex-message)
  - [Geohashing Services](#geohashing-services)
    - [Retrieve Geohash from Coordinates](#retrieve-geohash-from-coordinates)
    - [Retrieve Coordinates from Geohash](#retrieve-coordinates-from-geohash)
    - [Validate Coordinates with Geohash](#validate-coordinates-with-geohash)
- [HAProxy Monitoring](#haproxy-monitoring)

---

## Installation

To set up and run the Telex application, please follow these steps:

1. **Initialize Cluster:**
   Run the cluster initialization script:
   ```bash
   ./run_cluster.sh
   ```
2. **Start MongoDB:**
   Launch MongoDB by running:
   ```bash
   ./run_mongo.sh
   ```
3. **Start Telex Application:**
   Finally, start the Telex application:
   ```bash
   ./run.sh
   ```

**Note:** This setup leverages HAProxy for load balancing. You can monitor HAProxy stats by navigating to `http://127.0.0.1:8404/`.

---

## Usage

### Sending a Telex Message

To send a Telex message, make a request to the following endpoint:

```plaintext
GET http://localhost:8405/telex/api/send?telex=85
```

Alternatively, you can use `curl` to send a Telex message with JSON payload:

```bash
curl -X POST 'http://localhost:8405/telex/api/send' \
-H 'accept: */*' \
-H 'Content-Type: application/json' \
-d '{
    "name": "armen",
    "age": 45
}'
```

---

### Geohashing Services

The Telex application includes support for geohashing, allowing users to encode geographic locations into short strings and decode them back to coordinates. Below are the available geohashing endpoints:

#### Retrieve Geohash from Coordinates

To convert latitude and longitude into a geohash, use:

```plaintext
GET http://localhost:8405/telex/api/geohash?lat=40.177200&lon=44.503490
```

#### Retrieve Coordinates from Geohash

To decode a geohash back to its latitude and longitude, use:

```plaintext
GET http://localhost:8405/telex/api/location?geohash=szpssdn3nk
```

#### Validate Coordinates with Geohash

To check if a geohash corresponds to a specific latitude and longitude:

```plaintext
GET http://localhost:8405/telex/api/geohash/check?lat=40.177200&lon=44.503490&geohash=szpssdn3nk
```

---

## HAProxy Monitoring

This application uses **HAProxy** for efficient load balancing. To view the HAProxy statistics dashboard, open the following URL in your browser:

```plaintext
http://127.0.0.1:8404/
```

---

This README now provides a more user-friendly guide to installing, running, and using the Telex application, as well as enhanced documentation on endpoints and their usage examples.