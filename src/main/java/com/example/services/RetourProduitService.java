package com.example.services;

import com.example.entities.RetourProduit;
import com.example.entities.Produit;
import com.example.entities.Utilisateur;
import com.example.repositories.RetourProduitRepository;
import com.example.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RetourProduitService {

    @Autowired
    private RetourProduitRepository retourProduitRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private HistoriqueRetourService historiqueRetourService;

    @Autowired
    private UtilisateurService utilisateurService;

    // 🟢 This ID should be dynamically passed or from security context. For now it's hardcoded.
    private static final Long SYSTEM_EMPLOYE_ID = 1L;

    public List<RetourProduit> getAllRetours() {
        return retourProduitRepository.findAll();
    }

    public Optional<RetourProduit> getRetourById(Long id) {
        return retourProduitRepository.findById(id);
    }

    public RetourProduit saveRetour(RetourProduit retourProduit) {
        boolean isNew = (retourProduit.getId() == null);
        RetourProduit savedRetour = retourProduitRepository.save(retourProduit);

        // Logging logic
        String action = isNew ? "Création du retour" : "Mise à jour du retour";
        logHistorique(savedRetour, action);

        return savedRetour;
    }

    public void deleteRetour(Long id) {
        retourProduitRepository.findById(id).ifPresent(retour -> {
            retourProduitRepository.deleteById(id);
            logHistorique(retour, "Suppression du retour");
        });
    }

    private void logHistorique(RetourProduit retour, String action) {
        Utilisateur employe = utilisateurService.getUtilisateurById(SYSTEM_EMPLOYE_ID)
                .orElseThrow(() -> new RuntimeException("Employé système non trouvé"));
        historiqueRetourService.logReturnAction(retour, action, employe);
    }

    // Method to update the stock based on returns
    private void updateStock(Produit produit, int quantityReturned) {
        produit.setStock(produit.getStock() + quantityReturned);
        produitRepository.save(produit);
    }
}
