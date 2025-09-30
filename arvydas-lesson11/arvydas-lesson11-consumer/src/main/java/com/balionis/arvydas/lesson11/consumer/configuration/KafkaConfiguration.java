package com.balionis.arvydas.lesson11.consumer.configuration;

import com.balionis.arvydas.lesson11.avro.generated.v1.KafkaMessage;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.MicrometerConsumerListener;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfiguration {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, Object> consumerFactory(MeterRegistry meterRegistry) {
        // TODO: use SslBundle https://www.baeldung.com/spring-boot-kafka-ssl
        final Map<String, Object> props = kafkaProperties.buildConsumerProperties(null);
        DefaultKafkaConsumerFactory<String, Object> cf = new DefaultKafkaConsumerFactory<>(props);
        cf.addListener(new MicrometerConsumerListener<>(meterRegistry));
        return  cf;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> messageContainerFactory(
            ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.getContainerProperties().setObservationEnabled(true);
        factory.setConsumerFactory(consumerFactory);

        return factory;
    }
}
