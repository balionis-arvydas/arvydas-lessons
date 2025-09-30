package com.balionis.arvydas.lesson12.producer.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.balionis.arvydas.lesson12.producer.generated.model.AddMessageRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProducerServiceTest {

    private ProducerService producerService;

    @BeforeEach
    void setUp() {
        producerService = new ProducerService();
    }

    @Test
    public void should_add_message() {
        var addMessageRequest = new AddMessageRequest().failFactor(0);

        var addMessageResponse = producerService.addMessage(addMessageRequest);
        assertNotNull(addMessageResponse);
        assertNotNull(addMessageResponse.getMessageId());
    }
}