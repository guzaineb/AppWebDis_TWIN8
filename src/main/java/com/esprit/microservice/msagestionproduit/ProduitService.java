package com.esprit.microservice.msagestionproduit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProduitService implements ImpProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    public Produit updateProduit(int id, Produit newProduit) {
        return produitRepository.findById(id).map(existingProduit -> {
            existingProduit.setNom(newProduit.getNom());
            existingProduit.setDescription(newProduit.getDescription());
            existingProduit.setPrix(newProduit.getPrix());
            return produitRepository.save(existingProduit);
        }).orElse(null);
    }

    public String deleteProduit(int id) {
        if (produitRepository.findById(id).isPresent()) {
            produitRepository.deleteById(id);
            return "Produit supprimé";
        }
        return "Produit non trouvé";
    }
}

