package com.example.services;

import com.example.entities.NonConformite;
import com.example.repositories.NonConformiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NonConformiteService {

    @Autowired
    private NonConformiteRepository nonConformiteRepository;

    // CRUD operations
    public List<NonConformite> getAllNonConformites() {
        return nonConformiteRepository.findAll();
    }

    public Optional<NonConformite> getNonConformiteById(Long id) {
        return nonConformiteRepository.findById(id);
    }

    public NonConformite saveNonConformite(NonConformite nonConformite) {
        return nonConformiteRepository.save(nonConformite);
    }

    public void deleteNonConformite(Long id) {
        nonConformiteRepository.deleteById(id);
    }
}
