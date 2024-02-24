package com.balionis.dainius.lesson6.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.balionis.dainius.lesson6.generated.model.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

/**
 */
@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Test
    public void should_add() {
        var service = new PetService("service");
        var petId = service.addPet(new Pet("name"));

        assertThat(petId).isNotNull();
    }

    @Test
    public void should_delete() {
        var service = new PetService("service");
        service.deletePet(UUID.randomUUID());

        assertThat(true).isTrue();
    }

    @Test
    public void should_get() {
        var service = new PetService("service");
        service.getPet(UUID.randomUUID());

        assertThat(true).isTrue();
    }
}
