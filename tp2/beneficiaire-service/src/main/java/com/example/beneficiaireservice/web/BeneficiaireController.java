package com.example.beneficiaireservice.web;

import com.example.beneficiaireservice.entities.Beneficiaire;
import com.example.beneficiaireservice.repositories.BeneficiaireRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/beneficiaires")
public class BeneficiaireController {
    private BeneficiaireRepository beneficiaireRepository;

    public BeneficiaireController(BeneficiaireRepository beneficiaireRepository) {
        this.beneficiaireRepository = beneficiaireRepository;
    }

    @GetMapping
    public List<Beneficiaire> getAllBeneficiaires() {
        return beneficiaireRepository.findAll();
    }

    @GetMapping("/{id}")
    public Beneficiaire getBeneficiaire(@PathVariable Long id) {
        return beneficiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beneficiaire non trouvé"));
    }

    @PostMapping
    public Beneficiaire createBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        return beneficiaireRepository.save(beneficiaire);
    }

    @PutMapping("/{id}")
    public Beneficiaire updateBeneficiaire(@PathVariable Long id, @RequestBody Beneficiaire beneficiaire) {
        beneficiaire.setId(id);
        return beneficiaireRepository.save(beneficiaire);
    }

    @DeleteMapping("/{id}")
    public void deleteBeneficiaire(@PathVariable Long id) {
        beneficiaireRepository.deleteById(id);
    }
} 