package com.balionis.arvydas.lesson12.consumer.configuration;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.service")
@Slf4j
@Data
public class AppConfigurationProperties {

    private String name;

    @PostConstruct
    public void logPostConstruct() {
        log.info("appConfigurationProperties={}", this);
    }

}
