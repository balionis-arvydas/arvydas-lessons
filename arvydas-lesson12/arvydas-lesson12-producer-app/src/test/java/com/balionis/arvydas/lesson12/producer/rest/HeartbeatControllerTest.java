package com.balionis.arvydas.lesson12.producer.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.balionis.arvydas.lesson12.producer.Application;
import com.balionis.arvydas.lesson12.producer.generated.model.GetHeartbeatResponse;
import com.balionis.arvydas.lesson12.producer.service.HeartbeatService;
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

import java.time.Duration;
import java.time.OffsetDateTime;

@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class HeartbeatControllerTest {

    @MockBean
    private HeartbeatService heartbeatService;

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
    }

    @Test
    void should_heartbeat_ok() {
        when(heartbeatService.checkStatus()).thenReturn(OffsetDateTime.now());

        webTestClient.method(HttpMethod.GET)
                .uri("/api/v1/heartbeat")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(GetHeartbeatResponse.class)
                .consumeWith(result -> {
                    var body = result.getResponseBody();
                    assertThat(body).isNotNull();
                    assertThat(body.getCheckTs()).isAfter(OffsetDateTime.now().minus(Duration.ofSeconds(10)));
                });
    }
}