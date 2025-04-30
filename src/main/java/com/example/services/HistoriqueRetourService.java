package com.example.services;

import com.example.entities.HistoriqueRetour;
import com.example.entities.RetourProduit;
import com.example.entities.Utilisateur;
import com.example.repositories.HistoriqueRetourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoriqueRetourService {

    @Autowired
    private HistoriqueRetourRepository historiqueRetourRepository;

    // CRUD operations
    public List<HistoriqueRetour> getAllHistorique() {
        return historiqueRetourRepository.findAll();
    }

    public Optional<HistoriqueRetour> getHistoriqueById(Long id) {
        return historiqueRetourRepository.findById(id);
    }

    public HistoriqueRetour saveHistorique(HistoriqueRetour historiqueRetour) {
        return historiqueRetourRepository.save(historiqueRetour);
    }

    public void deleteHistorique(Long id) {
        historiqueRetourRepository.deleteById(id);
    }

    // Method to create a new action in the return history
    public void logReturnAction(RetourProduit retourProduit, String action, Utilisateur employe) {
        HistoriqueRetour historiqueRetour = new HistoriqueRetour(retourProduit, action, employe, LocalDateTime.now());
        saveHistorique(historiqueRetour);
    }
}
