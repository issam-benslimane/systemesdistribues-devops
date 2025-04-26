package com.example.virementservice.web;

import com.example.virementservice.entities.Virement;
import com.example.virementservice.repositories.VirementRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/virements")
public class VirementController {
    private VirementRepository virementRepository;

    public VirementController(VirementRepository virementRepository) {
        this.virementRepository = virementRepository;
    }

    @GetMapping
    public List<Virement> getAllVirements() {
        return virementRepository.findAll();
    }

    @GetMapping("/{id}")
    public Virement getVirement(@PathVariable Long id) {
        return virementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Virement non trouvé"));
    }

    @PostMapping
    public Virement createVirement(@RequestBody Virement virement) {
        return virementRepository.save(virement);
    }

    @PutMapping("/{id}")
    public Virement updateVirement(@PathVariable Long id, @RequestBody Virement virement) {
        virement.setId(id);
        return virementRepository.save(virement);
    }

    @DeleteMapping("/{id}")
    public void deleteVirement(@PathVariable Long id) {
        virementRepository.deleteById(id);
    }
} 