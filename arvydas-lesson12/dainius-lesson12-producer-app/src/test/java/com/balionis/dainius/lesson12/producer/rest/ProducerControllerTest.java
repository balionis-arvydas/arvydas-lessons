package com.balionis.dainius.lesson12.producer.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.balionis.dainius.lesson12.producer.Application;
import com.balionis.dainius.lesson12.producer.generated.model.AddMessageRequest;
import com.balionis.dainius.lesson12.producer.generated.model.AddMessageResponse;
import com.balionis.dainius.lesson12.producer.service.ProducerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

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
    void should_add_message_ok() {
        var messageId = UUID.randomUUID();
        var addMessageRequest = new AddMessageRequest().failFactor(0);
        var addMessageResponse = new AddMessageResponse().messageId(messageId);

        when(producerService.addMessage(addMessageRequest)).thenReturn(addMessageResponse);

        webTestClient.method(HttpMethod.POST)
                .uri("/api/v1/producer/message")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(addMessageRequest)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AddMessageResponse.class)
                .consumeWith(result -> {
                    var body = result.getResponseBody();
                    assertThat(body).isNotNull();
                    assertThat(body.getMessageId()).isEqualTo(messageId);
                });
    }
}