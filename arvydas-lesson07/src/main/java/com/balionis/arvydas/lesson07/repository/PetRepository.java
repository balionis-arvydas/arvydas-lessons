package com.balionis.arvydas.lesson07.repository;

import com.balionis.arvydas.lesson07.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends CrudRepository<PetEntity, String> {
}
