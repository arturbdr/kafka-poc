# KAFKA POC

This is a simple yet functional application that show how to work with a producer and a consumer with Apache Kafka.
 
## How to start it
It's necessary to have docker installed in the machine.
Simple pull this repository and docker-compose file. It will create a container with both Kafka and Zookeeper.
It's possible to access it in http://localhost:3030
Afterward, execute the SpringBoot class KafkaPocApplication.

Check http://localhost:8080/swagger-ui.html to send messages to Kafka

There's also an embedded Kafka Server for testing
