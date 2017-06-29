package com.poc.kafka.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class KafkaProperties {

  @Value("${kafka.url}")
  private String kafkaUrl;

  @Value("${kafka.producer.retries}")
  private int producerRetries;

  String getKafkaHostPort() {
    return getKafkaUrl();
  }
}
