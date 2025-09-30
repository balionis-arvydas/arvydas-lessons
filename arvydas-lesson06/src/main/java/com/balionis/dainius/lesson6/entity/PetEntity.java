package com.balionis.dainius.lesson6.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pets")
@Data
public class PetEntity {

    @Id
    @Column(name = "pet_id")
    private UUID petId;

    @Column
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}