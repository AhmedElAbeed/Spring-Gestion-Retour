package com.example.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class NonConformite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;  // Description of the non-conformity
    private String gravite;      // Severity of the non-conformity (e.g., high, medium, low)

    @Column(nullable = false)
    private LocalDate date;     // Date of the non-conformity

    @ManyToOne
    @JoinColumn(name = "produit_id", nullable = false)  // Link to a product
    private Produit produit;    // Reference to a Product entity

    // Default constructor
    public NonConformite() {}

    // Parameterized constructor
    public NonConformite(String description, String gravite, LocalDate date, Produit produit) {
        this.description = description;
        this.gravite = gravite;
        this.date = date;
        this.produit = produit;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGravite() {
        return gravite;
    }

    public void setGravite(String gravite) {
        this.gravite = gravite;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
