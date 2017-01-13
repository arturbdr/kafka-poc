# KAFKA POC

This is a simple yet functional application that show how to work with a producer and a consumer with Apache Kafka.
 
## How to start it
It's necessary to have docker installed in the machine.
Simple pull this repository and execute the script called: dockerstart.sh. It will create a container with both Kafka and Zookeeper.
Afterward, execute the SpringBoot class KafkaPocApplication.

Every 5 seconds a message will be produced to Kafka and Consumed.

## TODO
- [ ] Testing with Kafka (is there a Embedded Kafka for testing?)
- [ ] I'll create a simple docker-compose file
- [ ] Create more producer (posting in more topics)
- [ ] Create more complex Consumers (with different groups binding to different topics)
- [ ] Customize the prefetch and the commit of the consumers
