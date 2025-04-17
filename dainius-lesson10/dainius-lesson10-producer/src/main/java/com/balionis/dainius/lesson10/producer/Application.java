package com.balionis.dainius.lesson10.producer;

import com.balionis.dainius.lesson10.producer.configuration.AppConfigurationProperties;
import com.balionis.dainius.lesson10.producer.configuration.KafkaProducerConfigurationProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AppConfigurationProperties.class, KafkaProducerConfigurationProperties.class})
@Slf4j
public class Application {
    public static void main(String[] args) {
        log.info("starting");

        SpringApplication.run(Application.class, args);

        log.info("finishing");
    }
}

