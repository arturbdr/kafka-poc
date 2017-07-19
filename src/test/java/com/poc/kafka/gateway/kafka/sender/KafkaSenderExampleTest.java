package com.poc.kafka.gateway.kafka.sender;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.kafka.test.hamcrest.KafkaMatchers.hasValue;

import com.poc.kafka.config.KafkaTestConsumerConfig;
import com.poc.kafka.usecase.KafkaSampleUseCase;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaSenderExampleTest {

  private static String TEMPLATE_TOPIC = "TopicX";

  @ClassRule
  public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, 1, TEMPLATE_TOPIC);

  @Autowired
  private KafkaSampleUseCase kafkaSampleUseCase;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    System.setProperty("kafka.bootstrap-servers", embeddedKafka.getBrokersAsString());
  }

  @Test
  public void testTemplate() throws Exception {
    String sentMessage = "This is just an example message! ";

    DefaultKafkaConsumerFactory<String, String> cf =
        new DefaultKafkaConsumerFactory<>(KafkaTestConsumerConfig
            .consumerConfigs(embeddedKafka.getBrokersAsString(), "AnyNameHere", false));

    ContainerProperties containerProperties = new ContainerProperties(TEMPLATE_TOPIC);
    KafkaMessageListenerContainer<String, String> container =
        new KafkaMessageListenerContainer<>(cf, containerProperties);

    final BlockingQueue<ConsumerRecord<String, String>> records = new LinkedBlockingQueue<>();
    container.setupMessageListener((MessageListener<String, String>) records::add);
    container.start();
    ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());

    kafkaSampleUseCase.sendMessage(sentMessage);
    assertThat(records.poll(5, TimeUnit.SECONDS), hasValue(sentMessage));
  }

}