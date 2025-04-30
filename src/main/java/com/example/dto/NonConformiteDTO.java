package com.example.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class NonConformiteDTO {

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Severity is required")
    private String gravite;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Product ID is required")
    private Long produitId;

    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    // Getters and Setters
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGravite() { return gravite; }
    public void setGravite(String gravite) { this.gravite = gravite; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public Long getProduitId() { return produitId; }
    public void setProduitId(Long produitId) { this.produitId = produitId; }
}
