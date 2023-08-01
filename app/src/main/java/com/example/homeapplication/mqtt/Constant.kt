package com.example.homeapplication.mqtt

// Solace PubSub+ Broker Options

// Fill in your Solace Cloud PubSub+ Broker's 'MQTT Host' and 'Password' options.
// This information can be found under:
// https://console.solace.cloud/services/ -> <your-service> -> 'Connect' -> 'MQTT'
//const val SOLACE_CLIENT_USER_NAME = ""
//const val SOLACE_CLIENT_PASSWORD = ""
//const val SOLACE_MQTT_HOST = "mqtt://3.110.187.253:1883"

const val MQTT_HOST = "3.110.187.253"
const val MQTT_PORT = "1883"
const val MQTT_USERNAME = ""
const val MQTT_PASSWORD = ""

const val CONNECTION_TIMEOUT = 3
const val CONNECTION_KEEP_ALIVE_INTERVAL = 60
const val CONNECTION_CLEAN_SESSION = true
const val CONNECTION_RECONNECT = true


// Other options
//const val SOLACE_CONNECTION_TIMEOUT = 3
//const val SOLACE_CONNECTION_KEEP_ALIVE_INTERVAL = 60
//const val SOLACE_CONNECTION_CLEAN_SESSION = true
//const val SOLACE_CONNECTION_RECONNECT = true