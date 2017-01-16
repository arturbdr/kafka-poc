package com.poc.kafka.gateway.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Repository
@Slf4j
public class KafkaSenderExample {

    private final KafkaTemplate<Integer, String> kafkaTemplate;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

    @Value("${kafka.exampletopic}")
    private String kafkaTopicExample;

    @Autowired
    public KafkaSenderExample(KafkaTemplate<Integer, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedDelayString = "${application.scheduler.time}")
    public void produceMessage() {
        int randomInt = getRandomInt();
        LocalDateTime now = getNowDate();
        String msg = getParametrizedString();
        String formattedMsg = getFormattedMessage(msg, now, randomInt);

        sendMessage(kafkaTopicExample, formattedMsg);
    }

    private void sendMessage(String topic, String message) {
        ListenableFuture<SendResult<Integer, String>> future = kafkaTemplate.send(topic, message);

        future.addCallback(
                new ListenableFutureCallback<SendResult<Integer, String>>() {

                    @Override
                    public void onSuccess(
                            SendResult<Integer, String> result) {
                        log.info("Message sent ='{}' offset='{}'", message, result.getRecordMetadata().offset());
                    }

                    @Override
                    public void onFailure(Throwable ex) {
                        log.error("Failed to send message with content='{}'", message, ex);
                    }
                });

    }

    private LocalDateTime getNowDate() {
        return LocalDateTime.now();
    }

    private String getParametrizedString() {
        return "Random number {0} generated at {1}";
    }

    private String getFormattedMessage(String msg, LocalDateTime now, int randomInt) {
        return MessageFormat.format(msg, randomInt, dateTimeFormatter.format(now));
    }

    private int getRandomInt() {
        return new Random().nextInt();
    }
}
