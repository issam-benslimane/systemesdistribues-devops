package com.example.tp1.web;

import com.example.tp1.entities.Compte;
import com.example.tp1.repositories.CompteRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CompteGraphQLController {
    private CompteRepository compteRepository;

    public CompteGraphQLController(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @QueryMapping
    public List<Compte> compteList() {
        return compteRepository.findAll();
    }

    @QueryMapping
    public Compte compteById(@Argument String id) {
        return compteRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));
    }

    @MutationMapping
    public Compte addCompte(@Argument double solde, @Argument String type, @Argument String dateCreation) {
        Compte compte = new Compte();
        compte.setSolde(solde);
        compte.setType(type);
        compte.setDateCreation(dateCreation);
        return compteRepository.save(compte);
    }

    @MutationMapping
    public Compte updateCompte(@Argument String id, @Argument double solde, @Argument String type) {
        Compte compte = compteRepository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        compte.setSolde(solde);
        compte.setType(type);
        return compteRepository.save(compte);
    }

    @MutationMapping
    public boolean deleteCompte(@Argument String id) {
        compteRepository.deleteById(Long.parseLong(id));
        return true;
    }
} 