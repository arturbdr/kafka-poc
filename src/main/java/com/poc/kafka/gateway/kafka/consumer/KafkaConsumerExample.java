package com.poc.kafka.gateway.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@Profile("!test")
public class KafkaConsumerExample {

  @KafkaListener(topics = "${kafka.exampletopic}", containerFactory = "kafkaListenerContainerFactory")
  public void onMessage(String message) {
    log.info("Receiving message {}", message);
  }
}
