package com.poc.kafka.gateway.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class DefaultKafkaConsumer {

  @KafkaListener(topics = "${kafka.exampletopic}")
  public void onMessage(String message) {
    log.info("Receiving message {}", message);
  }
}
