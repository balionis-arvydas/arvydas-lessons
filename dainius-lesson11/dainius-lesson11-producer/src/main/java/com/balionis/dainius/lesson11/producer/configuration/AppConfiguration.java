package com.balionis.dainius.lesson11.producer.configuration;

import com.balionis.dainius.lesson11.avro.generated.v1.KafkaMessage;
import com.balionis.dainius.lesson11.producer.service.HeartbeatService;
import com.balionis.dainius.lesson11.producer.stream.KafkaProducer;
import com.balionis.dainius.lesson11.producer.stream.mappers.KafkaMessageMapper;
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

    @Bean
    public KafkaMessageMapper kafkaMessageMapper() {
        return new KafkaMessageMapper();
    }
}
