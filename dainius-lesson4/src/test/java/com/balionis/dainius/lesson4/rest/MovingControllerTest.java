package com.balionis.dainius.lesson4.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.balionis.dainius.lesson4.Application;
import com.balionis.dainius.lesson4.generated.model.AddNumberRequest;
import com.balionis.dainius.lesson4.generated.model.GetMovingResponse;
import com.balionis.dainius.lesson4.service.MovingService;
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

import java.util.Objects;

@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class MovingControllerTest {

    @MockBean
    private MovingService movingService;

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
    }

    @Test
    void should_add_number() {
        doNothing().when(movingService).add(anyDouble());

        webTestClient.method(HttpMethod.POST)
                .uri("/api/v1/moving")
                .body(Mono.just(new AddNumberRequest().number(9.0)), AddNumberRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .isEmpty();

        verify(movingService).add(9.0);
    }

    @Test
    void should_get_moving() {
        when(movingService.getMovingAverage()).thenReturn(4.66666);

        webTestClient.method(HttpMethod.GET)
                .uri("/api/v1/moving")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(GetMovingResponse.class)
                .consumeWith(response ->
                        assertThat(Objects.requireNonNull(response.getResponseBody()).getNumber())
                                .isEqualTo(4.666, within(0.001)));
        verify(movingService).getMovingAverage();
    }
}