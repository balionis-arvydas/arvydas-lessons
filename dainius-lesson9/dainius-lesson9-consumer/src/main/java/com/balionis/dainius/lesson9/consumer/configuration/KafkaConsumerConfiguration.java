package com.balionis.dainius.lesson9.consumer.configuration;

import com.balionis.dainius.lesson9.consumer.stream.mappers.KafkaMessageMapper;
import com.balionis.dainius.lesson9.consumer.stream.model.KafkaMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConsumerConfiguration {

    private final KafkaConsumerConfigurationProperties kafkaConsumerConfigurationProperties;

    @Bean
    public ConsumerFactory<String, KafkaMessage> kafkaConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                kafkaConsumerConfigurationProperties.getBootstrapServers());
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        configProps.put(JsonDeserializer.TYPE_MAPPINGS,
                "message:" + KafkaMessage.class.getName());

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, KafkaMessage>>
        kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, KafkaMessage> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumerFactory());
        factory.setConcurrency(kafkaConsumerConfigurationProperties.getConcurrency());
        factory.getContainerProperties().setPollTimeout(kafkaConsumerConfigurationProperties.getPollingTimeout());
        return factory;
    }

    @Bean
    public KafkaMessageMapper kafkaMessageMapper() {
        return Mappers.getMapper(KafkaMessageMapper.class);
    }

}
