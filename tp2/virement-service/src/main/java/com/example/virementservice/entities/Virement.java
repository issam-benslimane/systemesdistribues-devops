package com.example.virementservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Virement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long beneficiaireId;
    private String ribSource;
    private double montant;
    private String description;
    private Date dateVirement;
    @Enumerated(EnumType.STRING)
    private TypeVirement type;
}

enum TypeVirement {
    NORMAL,
    INSTANTANE
} 