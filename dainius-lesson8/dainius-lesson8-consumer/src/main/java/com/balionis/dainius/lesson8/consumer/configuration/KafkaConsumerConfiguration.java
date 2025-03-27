package com.balionis.dainius.lesson8.consumer.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final KafkaConsumerConfigurationProperties kafkaConsumerConfigurationProperties;

    @Bean
    public ConsumerFactory<String, String> kafkaConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaConsumerConfigurationProperties.getBootstrapServers());
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>>
        kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        factory.setConcurrency(kafkaConsumerConfigurationProperties.getConcurrency());
        factory.getContainerProperties().setPollTimeout(kafkaConsumerConfigurationProperties.getPollingTimeout());
        return factory;
    }
}

