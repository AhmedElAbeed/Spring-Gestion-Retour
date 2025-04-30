package com.example.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime; // Recommended to use LocalDateTime for better handling

@Entity
public class HistoriqueRetour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "retour_id", nullable = false)  // Link to RetourProduit entity
    private RetourProduit retour;

    private String action;  // Action performed on the return (e.g., approved, processed, etc.)

    @ManyToOne
    @JoinColumn(name = "employe_id", nullable = false)  // Link to Utilisateur entity
    private Utilisateur employe;

    private LocalDateTime date;  // Date and time of the action

    // Default constructor
    public HistoriqueRetour() {}

    // Parameterized constructor for easy instantiation
    public HistoriqueRetour(RetourProduit retour, String action, Utilisateur employe, LocalDateTime date) {
        this.retour = retour;
        this.action = action;
        this.employe = employe;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RetourProduit getRetour() {
        return retour;
    }

    public void setRetour(RetourProduit retour) {
        this.retour = retour;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Utilisateur getEmploye() {
        return employe;
    }

    public void setEmploye(Utilisateur employe) {
        this.employe = employe;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
