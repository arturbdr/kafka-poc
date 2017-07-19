package com.poc.kafka.gateway.kafka.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Repository
@Slf4j
@RequiredArgsConstructor
public class KafkaSenderExample {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {
    ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic,
        String.valueOf(message.hashCode()), message);

    send.addCallback(
        new ListenableFutureCallback<SendResult<String, String>>() {

          @Override
          public void onSuccess(
              SendResult<String, String> result) {
            log.info("Message successfully sent ='{}' offset='{}'", message,
                result.getRecordMetadata().offset());
          }

          @Override
          public void onFailure(Throwable ex) {
            log.error("Failed to send message with content='{}'", message, ex);
          }
        });

  }

}
