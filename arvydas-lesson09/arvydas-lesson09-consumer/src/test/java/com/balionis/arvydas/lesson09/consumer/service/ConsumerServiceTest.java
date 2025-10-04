package com.balionis.arvydas.lesson09.consumer.service;

import com.balionis.arvydas.lesson09.consumer.generated.model.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest {

    @Test
    public void should_read_empty() {
        var consumerService = new ConsumerService(0);
        assertThat(consumerService.readMessages(0)).hasSize(0);
    }

    @Test
    public void should_read_one() {
        var consumerService = new ConsumerService(10);
        var message = new Message(UUID.randomUUID(), "hello");
        consumerService.addMessage(message);
        assertThat(consumerService.readMessages(0)).hasSize(1).contains(message);
    }
}
