package com.balionis.arvydas.lesson09.consumer.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
@Slf4j
@Data
public class KafkaConsumerConfigurationProperties {

    private String bootstrapServers;

    private long pollingTimeout;

    private int concurrency;

    @PostConstruct
    public void logPostConstruct() {
        log.info("kafkaConsumerConfigurationProperties={}", this);
    }

}

