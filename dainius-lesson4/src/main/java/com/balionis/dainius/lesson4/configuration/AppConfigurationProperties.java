package com.balionis.dainius.lesson4.configuration;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.service")
@Slf4j
public class AppConfigurationProperties {

    private int limit;

    private String function;

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }

    @PostConstruct
    public void logPostConstruct() {
        log.info("limit={}, function={}", limit, function);
    }
}
