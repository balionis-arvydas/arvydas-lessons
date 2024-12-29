package com.balionis.dainius.lesson8.producer.configuration;

import com.balionis.dainius.lesson8.producer.service.HeartbeatService;
import com.balionis.dainius.lesson8.producer.stream.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

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
    public KafkaProducer kafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        return new KafkaProducer(kafkaTemplate,
                appConfigurationProperties.getKafka().getTopicName(),
                appConfigurationProperties.getKafka().getTimeoutSeconds());
    }
}
