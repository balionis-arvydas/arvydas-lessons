package com.balionis.dainius.lesson8.producer.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
@Slf4j
@Data
public class KafkaProducerConfigurationProperties {

    private String bootstrapServers;

    @PostConstruct
    public void logPostConstruct() {
        log.info("kafkaProducerConfigurationProperties={}", this);
    }
}
