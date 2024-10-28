# Telex

**Telex** is an international telegraphy-inspired system designed to support the transmission and receipt of printed messages over the public telecommunications network. This application simulates core Telex functionalities, including message transmission, geohashing, and location-based services. Additionally, it provides public and private real-time notification streaming through Server-Sent Events (SSE).

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
  - [Sending a Telex Message](#sending-a-telex-message)
  - [Geohashing Services](#geohashing-services)
    - [Retrieve Geohash from Coordinates](#retrieve-geohash-from-coordinates)
    - [Retrieve Coordinates from Geohash](#retrieve-coordinates-from-geohash)
    - [Validate Coordinates with Geohash](#validate-coordinates-with-geohash)
  - [Real-Time Streaming Services](#real-time-streaming-services)
    - [Public Notification Streaming](#public-notification-streaming)
    - [River Microservice Notifications](#river-microservice-notifications)
    - [Subscriber Microservice](#subscriber-microservice)
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

To decode a geohash back to its latitude and longitude:

```plaintext
GET http://localhost:8405/telex/api/location?geohash=szpssdn3nk
```

#### Validate Coordinates with Geohash

To check if a geohash corresponds to a specific latitude and longitude:

```plaintext
GET http://localhost:8405/telex/api/geohash/check?lat=40.177200&lon=44.503490&geohash=szpssdn3nk
```

---

### Real-Time Streaming Services

Telex provides real-time notification streaming services via Server-Sent Events (SSE), enabling both public and targeted notifications.

#### Public Notification Streaming

To receive public notifications, connect to the following endpoint with the desired `receiverId`:

```plaintext
GET http://localhost:2027/api/v2/notifications/public?receiverId=85
```

#### River Microservice Notifications

For river microservice notifications via SSE, connect to:

```plaintext
GET http://localhost:2027/api/v2/notifications?receiverId=101
```

#### Subscriber Microservice

To establish multiple concurrent SSE connections for notifications, use the subscriber microservice and define the number of desired connections with the `numConnections` parameter:

```plaintext
GET http://localhost:2028/api/v2/notifications?numConnections=2
```

---

## HAProxy Monitoring

This application uses **HAProxy** for efficient load balancing. To view the HAProxy statistics dashboard, open the following URL in your browser:

```plaintext
http://127.0.0.1:8404/
```

---

This enhanced README provides a more detailed and structured overview of Telex’s functionalities, from setup and geohashing to real-time streaming, making it easier to understand and use each feature.