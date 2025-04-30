package com.example.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class RetourProduitDTO {
    private Long id;  // Add this line

	

    @NotNull(message = "Produit ID is required")
    private Long produitId;

    @NotNull(message = "Client is required")
    private String client;

    @NotNull(message = "Reason for return is required")
    private String raison;

    @NotNull(message = "Return status is required")
    private String etatTraitement;

    @NotNull(message = "Return date is required")
    private LocalDate date;

    private Integer quantityReturned;  // No validation, can be null or zero

    // Getters and Setters
    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getEtatTraitement() {
        return etatTraitement;
    }

    public void setEtatTraitement(String etatTraitement) {
        this.etatTraitement = etatTraitement;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getQuantityReturned() {
        return quantityReturned;
    }

    public void setQuantityReturned(Integer quantityReturned) {
        this.quantityReturned = quantityReturned;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
