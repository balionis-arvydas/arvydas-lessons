package com.balionis.dainius.lesson6.service;

import com.balionis.dainius.lesson6.generated.model.Pet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class PetService {

    private final String name;

    public UUID addPet(Pet pet) {
        log.info("name={}, pet={}", name, pet);
        return UUID.randomUUID();
    }

    public void deletePet(UUID petId) {
        log.info("name={}, petId={}", name, petId);
    }

    public Pet getPet(UUID petId) {
        log.info("name={}, petId={}", name, petId);
        return null;
    }
}
