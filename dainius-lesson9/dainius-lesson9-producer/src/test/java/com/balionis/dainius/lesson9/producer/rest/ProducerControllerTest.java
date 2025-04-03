package com.balionis.dainius.lesson9.producer.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.balionis.dainius.lesson9.producer.Application;
import com.balionis.dainius.lesson9.producer.generated.model.SendMessageRequest;
import com.balionis.dainius.lesson9.producer.service.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class ProducerControllerTest {

    @MockBean
    private ProducerService producerService;

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
    }

    @Test
    void should_send_message() {

        var request = new SendMessageRequest().messageId(UUID.randomUUID()).message("hello");

        doNothing().when(producerService).sendMessage(any(SendMessageRequest.class));

        webTestClient.method(HttpMethod.POST)
                .uri("/api/v1/producer/message")
                .body(Mono.just(request), SendMessageRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .isEmpty();
        verify(producerService).sendMessage(request);
    }
}