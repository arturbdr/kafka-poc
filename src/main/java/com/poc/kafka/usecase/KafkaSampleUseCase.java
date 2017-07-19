package com.poc.kafka.usecase;

import com.poc.kafka.gateway.kafka.sender.KafkaSenderExample;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaSampleUseCase {

  private final KafkaSenderExample kafkaSenderExample;

  @Value("${kafka.exampletopic}")
  private String kafkaTopicExample;

  public void sendMessage(String message) {
    kafkaSenderExample.sendMessage(kafkaTopicExample, message);
  }
}
