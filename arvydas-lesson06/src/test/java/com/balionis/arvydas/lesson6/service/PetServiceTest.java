package com.balionis.arvydas.lesson6.service;

import static com.balionis.arvydas.lesson6.TestHelper.createMockPetEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.balionis.arvydas.lesson6.entity.PetEntity;
import com.balionis.arvydas.lesson6.entity.PetStatus;
import com.balionis.arvydas.lesson6.generated.model.Pet;
import com.balionis.arvydas.lesson6.repository.PetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

/**
 */
@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    private PetService petService;

    @BeforeEach
    void setUp() {
        petService = new PetService("myService", petRepository);
    }

    @Test
    public void should_add() {

        var petEntity = createMockPetEntity(UUID.randomUUID());

        when(petRepository.save(any(PetEntity.class))).thenReturn(petEntity);

        var pet = new Pet("myPet").status(Pet.StatusEnum.AVAILABLE);

        var petId = petService.addPet(pet);

        assertThat(petId).isNotNull();
    }

    @Test
    public void should_delete() {
        doNothing().when(petRepository).deleteById(any(UUID.class));

        petService.deletePet(UUID.randomUUID());

        assertThat(true).isTrue();
    }

    @Test
    public void should_get() {

        var petId = UUID.randomUUID();

        var petEntity = createMockPetEntity(petId);

        when(petRepository.findById(petId)).thenReturn(Optional.of(petEntity));

        petService.getPet(petId);

        assertThat(true).isTrue();
    }
}
