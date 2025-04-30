package com.example.controllers;

import com.example.dto.HistoriqueRetourDTO;
import com.example.entities.HistoriqueRetour;
import com.example.entities.RetourProduit;
import com.example.entities.Utilisateur;
import com.example.services.HistoriqueRetourService;
import com.example.services.RetourProduitService;
import com.example.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historique-retour")
@CrossOrigin(origins = "*") 
@Validated
public class HistoriqueRetourController {

    @Autowired
    private HistoriqueRetourService historiqueRetourService;

    @Autowired
    private RetourProduitService retourProduitService;

    @Autowired
    private UtilisateurService utilisateurService;

    // Get all records and return DTOs
    @GetMapping
    public List<HistoriqueRetourDTO> getAllHistorique() {
        List<HistoriqueRetour> historiques = historiqueRetourService.getAllHistorique();
        return historiques.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a single record by ID
    @GetMapping("/{id}")
    public ResponseEntity<HistoriqueRetourDTO> getHistoriqueById(@PathVariable Long id) {
        Optional<HistoriqueRetour> historique = historiqueRetourService.getHistoriqueById(id);
        return historique.map(h -> ResponseEntity.ok(convertToDTO(h)))
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new record using DTO
    @PostMapping
    public ResponseEntity<HistoriqueRetourDTO> createHistorique(@Valid @RequestBody HistoriqueRetourDTO dto) {
        // Convert DTO to Entity
        HistoriqueRetour historiqueRetour = new HistoriqueRetour();
        historiqueRetour.setAction(dto.getAction());
        historiqueRetour.setDate(dto.getDate());

        // Convert IDs to actual entities
        RetourProduit retour = retourProduitService.getRetourById(dto.getRetourId())
            .orElseThrow(() -> new RuntimeException("Retour not found"));
        Utilisateur employe = utilisateurService.getUtilisateurById(dto.getEmployeId())
            .orElseThrow(() -> new RuntimeException("Employe not found"));

        historiqueRetour.setRetour(retour);
        historiqueRetour.setEmploye(employe);

        // Save entity and return DTO
        HistoriqueRetour savedHistorique = historiqueRetourService.saveHistorique(historiqueRetour);
        return ResponseEntity.ok(convertToDTO(savedHistorique));
    }

    // Delete a record
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHistorique(@PathVariable Long id) {
        Optional<HistoriqueRetour> historique = historiqueRetourService.getHistoriqueById(id);
        if (historique.isPresent()) {
            historiqueRetourService.deleteHistorique(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // Helper method: Convert Entity -> DTO
    private HistoriqueRetourDTO convertToDTO(HistoriqueRetour historique) {
        HistoriqueRetourDTO dto = new HistoriqueRetourDTO();
        dto.setId(historique.getId());  // <-- important
        dto.setRetourId(historique.getRetour().getId());
        dto.setAction(historique.getAction());
        dto.setEmployeId(historique.getEmploye().getId());
        dto.setDate(historique.getDate());
        return dto;
    }
}
