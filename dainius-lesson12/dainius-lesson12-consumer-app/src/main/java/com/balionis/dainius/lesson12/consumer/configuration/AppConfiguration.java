package com.balionis.dainius.lesson12.consumer.configuration;

import com.balionis.dainius.lesson12.consumer.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final AppConfigurationProperties appConfigurationProperties;

    @Bean
    public HeartbeatService heartbeatService() {
        return new HeartbeatService(appConfigurationProperties.getName());
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
