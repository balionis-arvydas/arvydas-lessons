package com.balionis.dainius.lesson9.consumer.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.service")
@Slf4j
@Data
public class AppConfigurationProperties {

    private String name;

    private int pageSize;

    private KafkaConfigurationProperties kafka;

    @PostConstruct
    public void logPostConstruct() {
        log.info("appConfigurationProperties={}", this);
    }

    @Data
    public static class KafkaConfigurationProperties {
        private String topicName;
    }
}
