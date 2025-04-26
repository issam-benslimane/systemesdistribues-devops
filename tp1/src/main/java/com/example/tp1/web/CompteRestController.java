package com.example.tp1.web;

import com.example.tp1.entities.Compte;
import com.example.tp1.repositories.CompteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompteRestController {
    private CompteRepository compteRepository;

    public CompteRestController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @GetMapping("/comptes")
    public List<Compte> listComptes() {
        return compteRepository.findAll();
    }

    @GetMapping("/comptes/{id}")
    public Compte getCompte(@PathVariable Long id) {
        return compteRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
    }

    @PostMapping("/comptes")
    public Compte save(@RequestBody Compte compte) {
        return compteRepository.save(compte);
    }

    @PutMapping("/comptes/{id}")
    public Compte update(@PathVariable Long id, @RequestBody Compte compte) {
        compte.setId(id);
        return compteRepository.save(compte);
    }

    @DeleteMapping("/comptes/{id}")
    public void delete(@PathVariable Long id) {
        compteRepository.deleteById(id);
    }
} 