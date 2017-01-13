package com.poc.kafka.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class KafkaProperties {

    @Value("${kafka.host}")
    private String kafkaHost;

    @Value("${kafka.port}")
    private String kafkaPort;

    String getKafkaHostPort() {
        return getKafkaHost() + ":" + getKafkaPort();
    }
}
