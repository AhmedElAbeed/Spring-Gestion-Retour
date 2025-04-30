package com.example.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class RetourProduit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false) // Foreign key to Produit
    private Produit produit; // Reference to the Produit entity
    
    private String client;  // Name or details of the client who made the return
    private String raison;  // Reason for the return
    private String etatTraitement; // Status of the return (e.g., processed, pending)
    
    @Column(nullable = false)
    private LocalDate date;  // Date of the return

    // Default constructor
    public RetourProduit() {}

    // Parameterized constructor
    public RetourProduit(Produit produit, String client, String raison, String etatTraitement, LocalDate date) {
        this.produit = produit;
        this.client = client;
        this.raison = raison;
        this.etatTraitement = etatTraitement;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
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
}
