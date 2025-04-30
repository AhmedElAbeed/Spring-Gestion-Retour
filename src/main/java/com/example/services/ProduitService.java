package com.example.services;

import com.example.entities.Produit;
import com.example.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    private final ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // Retrieve all products
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // Retrieve a product by its ID
    public Optional<Produit> getProduitById(Long id) {
        return produitRepository.findById(id);
    }

    // Save or update a product
    public Produit saveProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Delete a product by its ID
    public void deleteProduit(Long id) {
        produitRepository.deleteById(id);
    }
}
