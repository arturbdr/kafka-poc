package com.poc.kafka.gateway.http;

import com.poc.kafka.usecase.KafkaSampleUseCase;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleKafkaTestController {

  private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
      .ofPattern("yyyy-MM-dd hh:mm:ss");
  private final KafkaSampleUseCase kafkaSampleUseCase;

  @ApiOperation("Publish the typed path message do Kafka")
  @PostMapping("/sendMessage/{message}")
  public void sendMessageToKafka(
      @ApiParam(value = "${SampleKafkaTestController.sendMessageToKafka.message}", required = true)
      @PathVariable String message) {
    kafkaSampleUseCase.sendMessage(message);
  }

  @ApiOperation(value = "Generate random message to Kafka", response = String.class)
  @PostMapping(value = "/randomMessage/", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<String> sendRandomMessageToKafka() {
    String messageProduced = produceMessage();
    kafkaSampleUseCase.sendMessage(messageProduced);
    return ResponseEntity.ok(messageProduced);
  }

  private String produceMessage() {
    int randomInt = getRandomInt();
    LocalDateTime now = getNowDate();
    String msg = getParametrizedString();
    return getFormattedMessage(msg, now, randomInt);

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
    return new Random().nextInt(100);
  }

}
