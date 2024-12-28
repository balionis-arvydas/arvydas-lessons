package com.balionis.dainius.lesson8.consumer.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ConsumerServiceTest {

    @Test
    public void should_read_empty() {
        var consumerService = new ConsumerService(0);
        assertThat(consumerService.readMessages(0)).hasSize(0);
    }
}
