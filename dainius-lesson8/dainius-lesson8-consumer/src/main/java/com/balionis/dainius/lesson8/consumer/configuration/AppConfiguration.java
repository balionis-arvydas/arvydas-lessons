package com.balionis.dainius.lesson8.consumer.configuration;

import com.balionis.dainius.lesson8.consumer.service.ConsumerService;
import com.balionis.dainius.lesson8.consumer.service.HeartbeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AppConfiguration {

    private final AppConfigurationProperties appConfigurationProperties;

    @Bean
    public HeartbeatService heartbeatService() {
        return new HeartbeatService(appConfigurationProperties.getName());
    }

    @Bean
    public ConsumerService consumerService() {
        return new ConsumerService(appConfigurationProperties.getPageSize());
    }
}
