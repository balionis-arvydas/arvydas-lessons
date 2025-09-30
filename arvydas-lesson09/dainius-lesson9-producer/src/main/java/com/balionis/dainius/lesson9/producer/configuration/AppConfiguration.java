package com.balionis.dainius.lesson9.producer.configuration;

import com.balionis.dainius.lesson9.producer.service.HeartbeatService;
import com.balionis.dainius.lesson9.producer.stream.KafkaProducer;
import com.balionis.dainius.lesson9.producer.stream.mappers.KafkaMessageMapper;
import com.balionis.dainius.lesson9.producer.stream.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final AppConfigurationProperties appConfigurationProperties;

    @Bean
    public HeartbeatService heartbeatService() {
        return new HeartbeatService(appConfigurationProperties.getName());
    }

    @Bean
    public KafkaProducer kafkaProducer(KafkaTemplate<String, KafkaMessage> kafkaTemplate,
                                       KafkaMessageMapper kafkaMessageMapper) {
        return new KafkaProducer(kafkaTemplate,
                appConfigurationProperties.getKafka().getTopicName(),
                appConfigurationProperties.getKafka().getTimeoutSeconds(),
                kafkaMessageMapper);
    }
}
