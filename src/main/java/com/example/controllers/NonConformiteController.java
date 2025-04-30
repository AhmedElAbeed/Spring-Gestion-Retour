package com.example.controllers;

import com.example.dto.NonConformiteDTO;
import com.example.entities.NonConformite;
import com.example.entities.Produit;
import com.example.services.NonConformiteService;
import com.example.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/non-conformite")
@CrossOrigin(origins = "*")
public class NonConformiteController {

    @Autowired
    private NonConformiteService nonConformiteService;

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<NonConformiteDTO> getAllNonConformites() {
        return nonConformiteService.getAllNonConformites().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NonConformiteDTO> getNonConformiteById(@PathVariable Long id) {
        return nonConformiteService.getNonConformiteById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NonConformiteDTO> createNonConformite(@Valid @RequestBody NonConformiteDTO dto) {
        Produit produit = produitService.getProduitById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit not found"));

        NonConformite nonConformite = new NonConformite(dto.getDescription(), dto.getGravite(), dto.getDate(), produit);
        NonConformite saved = nonConformiteService.saveNonConformite(nonConformite);
        return ResponseEntity.ok(convertToDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NonConformiteDTO> updateNonConformite(@PathVariable Long id, @Valid @RequestBody NonConformiteDTO dto) {
        Optional<NonConformite> existing = nonConformiteService.getNonConformiteById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produit produit = produitService.getProduitById(dto.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit not found"));

        NonConformite nonConformite = existing.get();
        nonConformite.setDescription(dto.getDescription());
        nonConformite.setGravite(dto.getGravite());
        nonConformite.setDate(dto.getDate());
        nonConformite.setProduit(produit);

        NonConformite updated = nonConformiteService.saveNonConformite(nonConformite);
        return ResponseEntity.ok(convertToDTO(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNonConformite(@PathVariable Long id) {
        if (nonConformiteService.getNonConformiteById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        nonConformiteService.deleteNonConformite(id);
        return ResponseEntity.noContent().build();
    }

    private NonConformiteDTO convertToDTO(NonConformite nc) {
        NonConformiteDTO dto = new NonConformiteDTO();
        dto.setId(nc.getId());
        dto.setDescription(nc.getDescription());
        dto.setGravite(nc.getGravite());
        dto.setDate(nc.getDate());
        dto.setProduitId(nc.getProduit().getId());
        return dto;
    }
}
