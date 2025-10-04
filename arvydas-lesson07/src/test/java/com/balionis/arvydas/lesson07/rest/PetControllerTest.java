package com.balionis.arvydas.lesson07.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.balionis.arvydas.lesson07.Application;
import com.balionis.arvydas.lesson07.generated.model.AddPetRequest;
import com.balionis.arvydas.lesson07.generated.model.AddPetResponse;
import com.balionis.arvydas.lesson07.generated.model.Pet;
import com.balionis.arvydas.lesson07.service.PetService;
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
import java.util.UUID;

@ActiveProfiles("test")
@SpringBootTest(classes = {Application.class}, webEnvironment = RANDOM_PORT)
@AutoConfigureWebTestClient
class PetControllerTest {

    @MockBean
    private PetService petService;

    @Autowired
    private WebTestClient webTestClient;

    @LocalServerPort
    private int randomServerPort;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + randomServerPort).build();
    }

    @Test
    void should_add_pet() {
        var petId = UUID.randomUUID();
        var pet = new Pet().id(petId).name("petName").status(Pet.StatusEnum.PENDING);

        when(petService.addPet(pet)).thenReturn(petId);

        webTestClient.method(HttpMethod.POST)
                .uri("/api/v1/pet")
                .body(Mono.just(new AddPetRequest().pet(pet)), AddPetRequest.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(AddPetResponse.class)
                .consumeWith(response ->
                        assertThat(Objects.requireNonNull(response.getResponseBody()).getId()).isEqualTo(petId));

        verify(petService).addPet(pet);
    }

    @Test
    void should_get_pet() {
        var petId = UUID.randomUUID();

        var pet = new Pet().id(petId).name("petName").status(Pet.StatusEnum.PENDING);

        when(petService.getPet(any(UUID.class))).thenReturn(pet);

        webTestClient.method(HttpMethod.GET)
                .uri("/api/v1/pet/" + petId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Pet.class)
                .consumeWith(response ->
                        assertThat(Objects.requireNonNull(response.getResponseBody())).isEqualTo(pet));

        verify(petService).getPet(any(UUID.class));
    }

    @Test
    void should_delete_pet() {
        var petId = UUID.randomUUID();

        doNothing().when(petService).deletePet(any(UUID.class));

        webTestClient.method(HttpMethod.DELETE)
                .uri("/api/v1/pet/" + petId)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .isEmpty();

        verify(petService).deletePet(petId);
    }
}