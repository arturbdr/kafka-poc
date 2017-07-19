package com.poc.kafka.config.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "kafka")
@Profile("!test")
public class KafkaProperties {

  private String url;
  private int producerRetries;
  private String consumerGroup;
  private int consumers;
}
