package com.example.controllers;

import com.example.dto.RetourProduitDTO;
import com.example.entities.RetourProduit;
import com.example.entities.Produit;
import com.example.services.RetourProduitService;
import com.example.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/retour-produit")
@CrossOrigin(origins = "*") // Allow frontend access
public class RetourProduitController {

    @Autowired
    private RetourProduitService retourProduitService;

    @Autowired
    private ProduitService produitService;

    // Get all RetourProduit entries
    @GetMapping
    public List<RetourProduitDTO> getAllRetours() {
        List<RetourProduit> retours = retourProduitService.getAllRetours();
        return retours.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Get a specific RetourProduit by ID
    @GetMapping("/{id}")
    public ResponseEntity<RetourProduitDTO> getRetourById(@PathVariable Long id) {
        Optional<RetourProduit> retourProduit = retourProduitService.getRetourById(id);
        return retourProduit.map(this::convertToDTO)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RetourProduitDTO> createRetour(@Valid @RequestBody RetourProduitDTO retourProduitDTO) {
        System.out.println("🟢 Received RetourProduitDTO: " + retourProduitDTO);

        // Validate produitId and map it to Produit entity
        Produit produit = produitService.getProduitById(retourProduitDTO.getProduitId())
                .orElseThrow(() -> new RuntimeException("⚠️ Produit not found with ID: " + retourProduitDTO.getProduitId()));

        // Handle quantityReturned based on etatTraitement
        if ("PENDING".equals(retourProduitDTO.getEtatTraitement())) {
            retourProduitDTO.setQuantityReturned(null); // No quantity for "PENDING"
        } else if ("ACCEPTED".equals(retourProduitDTO.getEtatTraitement()) && retourProduitDTO.getQuantityReturned() != null) {
            // Validate the quantity when the state is ACCEPTED
            if (retourProduitDTO.getQuantityReturned() <= 0) {
                throw new RuntimeException("⚠️ Quantity must be positive when the return is accepted.");
            }
        }

        // Convert DTO to Entity and set etatTraitement to "Pending"
        RetourProduit retourProduit = new RetourProduit();
        retourProduit.setProduit(produit);
        retourProduit.setClient(retourProduitDTO.getClient());
        retourProduit.setRaison(retourProduitDTO.getRaison());
        retourProduit.setEtatTraitement("Pending"); // Default to "Pending"
        retourProduit.setDate(retourProduitDTO.getDate());

        retourProduitService.saveRetour(retourProduit);

        return ResponseEntity.ok(convertToDTO(retourProduit));
    }

 @PutMapping("/{id}")
    public ResponseEntity<RetourProduitDTO> updateRetour(@PathVariable Long id, @Valid @RequestBody RetourProduitDTO retourProduitDTO) {
        Optional<RetourProduit> existingRetour = retourProduitService.getRetourById(id);
        if (!existingRetour.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Get the related product from DB
        Produit produit = produitService.getProduitById(retourProduitDTO.getProduitId())
                .orElseThrow(() -> new RuntimeException("Produit not found"));

        // Handle quantityReturned based on etatTraitement
        if ("PENDING".equals(retourProduitDTO.getEtatTraitement())) {
            retourProduitDTO.setQuantityReturned(null); // No quantity for "PENDING"
        } else if ("ACCEPTED".equals(retourProduitDTO.getEtatTraitement()) && retourProduitDTO.getQuantityReturned() != null) {
            // Validate the quantity when the state is ACCEPTED
            if (retourProduitDTO.getQuantityReturned() <= 0) {
                throw new RuntimeException("Quantity must be positive when the return is accepted.");
            }
        }

        // Update the RetourProduit entity
        RetourProduit retourProduit = existingRetour.get();
        retourProduit.setProduit(produit);
        retourProduit.setClient(retourProduitDTO.getClient());
        retourProduit.setRaison(retourProduitDTO.getRaison());
        retourProduit.setEtatTraitement(retourProduitDTO.getEtatTraitement());
        retourProduit.setDate(retourProduitDTO.getDate());

        // Update Stock If Necessary (Only if Accepted)
        if ("ACCEPTED".equalsIgnoreCase(retourProduitDTO.getEtatTraitement()) && retourProduitDTO.getQuantityReturned() != null) {
            produit.setStock(produit.getStock() + retourProduitDTO.getQuantityReturned()); // Add quantity to the stock
            produitService.saveProduit(produit); // Save updated product to reflect new stock
        }

        retourProduitService.saveRetour(retourProduit);

        return ResponseEntity.ok(convertToDTO(retourProduit));
    }
    
    
    
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetour(@PathVariable Long id) {
        if (!retourProduitService.getRetourById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        retourProduitService.deleteRetour(id);
        return ResponseEntity.noContent().build();
    }

    // Helper method to convert RetourProduit entity to DTO
    private RetourProduitDTO convertToDTO(RetourProduit retourProduit) {
        RetourProduitDTO dto = new RetourProduitDTO();
        dto.setId(retourProduit.getId());  // Make sure ID is included
        dto.setProduitId(retourProduit.getProduit().getId());
        dto.setClient(retourProduit.getClient());
        dto.setRaison(retourProduit.getRaison());
        dto.setEtatTraitement(retourProduit.getEtatTraitement());
        dto.setDate(retourProduit.getDate());
        return dto;
    }
}
