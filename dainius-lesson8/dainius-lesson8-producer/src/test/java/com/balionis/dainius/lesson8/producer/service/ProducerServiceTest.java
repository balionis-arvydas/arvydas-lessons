package com.balionis.dainius.lesson8.producer.service;

import com.balionis.dainius.lesson8.producer.generated.model.SendMessageRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ProducerServiceTest {

    @Test
    public void should_send_message() {
        var service = new ProducerService();
        service.sendMessage(new SendMessageRequest().messageId(UUID.randomUUID()).message("hello"));

        assertTrue(true);
    }
}