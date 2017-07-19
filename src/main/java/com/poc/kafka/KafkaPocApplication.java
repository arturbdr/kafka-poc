package com.poc.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableKafka
@EnableSwagger2
public class KafkaPocApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaPocApplication.class, args);
    }



}
