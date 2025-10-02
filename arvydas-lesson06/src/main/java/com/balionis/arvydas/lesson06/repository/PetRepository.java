package com.balionis.arvydas.lesson06.repository;

import com.balionis.arvydas.lesson06.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, UUID> {
}
