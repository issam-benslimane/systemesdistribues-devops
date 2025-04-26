package com.example.beneficiaireservice.repositories;

import com.example.beneficiaireservice.entities.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeneficiaireRepository extends JpaRepository<Beneficiaire, Long> {
} 