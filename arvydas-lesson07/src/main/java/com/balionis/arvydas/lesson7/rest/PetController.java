package com.balionis.arvydas.lesson7.rest;

import com.balionis.arvydas.lesson7.generated.api.PetApi;
import com.balionis.arvydas.lesson7.generated.model.AddPetRequest;
import com.balionis.arvydas.lesson7.generated.model.AddPetResponse;
import com.balionis.arvydas.lesson7.generated.model.Pet;
import com.balionis.arvydas.lesson7.service.PetService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class PetController implements PetApi {

    private final PetService petService;

    @Override
    public ResponseEntity<AddPetResponse> addPet(
            @Parameter(name = "Pet", required = true) @Valid @RequestBody AddPetRequest request
    ) {
        log.info("request={}", request);
        var petId = petService.addPet(request.getPet());
        return ResponseEntity.ok(new AddPetResponse().id(petId));
    }

    @Override
    public ResponseEntity<Void> deletePet(
            @Parameter(name = "petId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("petId") UUID petId
    ) {
        log.info("petId={}", petId);
        petService.deletePet(petId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Pet> getPetById(
            @Parameter(name = "petId", description = "", required = true, in = ParameterIn.PATH) @PathVariable("petId") UUID petId
    ) {
        log.info("petId={}", petId);
        var pet = petService.getPet(petId);
        return ResponseEntity.ok(pet);
    }
}
