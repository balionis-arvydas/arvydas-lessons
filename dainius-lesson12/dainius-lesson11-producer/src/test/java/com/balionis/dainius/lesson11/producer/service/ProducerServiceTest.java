package com.balionis.dainius.lesson11.producer.service;

import com.balionis.dainius.lesson11.producer.generated.model.SendMessageRequest;
import com.balionis.dainius.lesson11.producer.stream.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProducerServiceTest {

    @Test
    public void should_send_message() {
        var kafkaProducer = mock(KafkaProducer.class);
        doNothing().when(kafkaProducer).sendMessage(any());

        var service = new ProducerService(kafkaProducer);
        service.sendMessage(new SendMessageRequest().messageId(UUID.randomUUID()).message("hello"));

        verify(kafkaProducer).sendMessage(any());
    }
}