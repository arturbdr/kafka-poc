package com.poc.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {

  private String url;
  private int producerRetries;
  private String consumerGroup;
}
