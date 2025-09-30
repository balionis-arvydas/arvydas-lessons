package com.balionis.arvydas.lesson8.consumer.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.balionis.arvydas.lesson8.consumer.Application;
import com.balionis.arvydas.lesson8.consumer.generated.model.GetMessageResponse;
import com.balionis.arvydas.lesson8.consumer.service.ConsumerService;
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

import java.util.List;
import java.util.Objects;

@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class ConsumerControllerTest {

    @MockBean
    private ConsumerService consumerService;

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
    }

    @Test
    void should_read_empty() {
        when(consumerService.readMessages(0)).thenReturn(List.of());

        webTestClient.method(HttpMethod.GET)
                .uri("/api/v1/consumer/message")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(GetMessageResponse.class)
                .consumeWith(response ->
                        assertThat(Objects.requireNonNull(response.getResponseBody()).getMessages()
                                .isEmpty()));

        verify(consumerService).readMessages(0);
    }
}