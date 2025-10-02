package com.balionis.arvydas.lesson06.repository;

import com.balionis.arvydas.lesson06.entity.PetEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static com.balionis.arvydas.lesson06.TestHelper.createMockPetEntity;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@ActiveProfiles("test")
@EnableAutoConfiguration
@ExtendWith(SpringExtension.class)
@EntityScan(basePackageClasses = PetEntity.class)
@EnableJpaRepositories(basePackageClasses = {PetRepository.class})
class PetRepositoryTest {

    @Autowired
    private PetRepository petRepository;

    @BeforeEach
    @AfterEach
    void tearDown() {
        petRepository.deleteAll();
    }

    @Test
    void crud_pet_entity_success() {
        var petId = UUID.randomUUID();

        var petEntitySaved = petRepository.save(createMockPetEntity(petId));

        var petEntityFound = petRepository.findById(petId)
                .orElseGet(() -> fail("No record found"));

        assertThat(petRepository.count()).isEqualTo(1);
        assertTrue(new ReflectionEquals(petEntitySaved).matches(petEntityFound));

        petRepository.deleteById(petId);
        assertThat(petRepository.count()).isEqualTo(0);

    }
}
