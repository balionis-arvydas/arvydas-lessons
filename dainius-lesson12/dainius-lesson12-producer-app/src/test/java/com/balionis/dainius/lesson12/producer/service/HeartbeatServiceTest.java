package com.balionis.dainius.lesson12.producer.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;

/**
 */
@ExtendWith(MockitoExtension.class)
public class HeartbeatServiceTest {

    private HeartbeatService heartbeatService;

    @BeforeEach
    void setUp() {
        heartbeatService = new HeartbeatService("myService");
    }

    @Test
    public void should_get() {
        assertThat(heartbeatService.checkStatus()).isAfter(OffsetDateTime.now().minus(Duration.ofSeconds(10)));
    }
}