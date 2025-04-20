package com.esprit.microservice.msagestionproduit.produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService implements ImpProduitService {

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);


    }

    @Override
    public Produit updateProduit(int id, Produit newProduit) {
        return produitRepository.findById(id).map(existingProduit -> {
            existingProduit.setNom(newProduit.getNom());
            existingProduit.setDescription(newProduit.getDescription());
            existingProduit.setPrix(newProduit.getPrix());
            existingProduit.setQuantiteDisponible(newProduit.getQuantiteDisponible());
            return produitRepository.save(existingProduit);
        }).orElse(null);
    }

    @Override
    public String deleteProduit(int id) {
        if (produitRepository.existsById(id)) {
            produitRepository.deleteById(id);
            return "Produit supprimé avec succès";
        }
        return "Produit non trouvé";
    }

    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public  List<Produit> getProduitByNom(String nom) {
        return produitRepository.findAllByNom(nom);
    }

    @Override
    public List<Produit> getProduitsByPrixRange(double min, double max) {
        return produitRepository.findByPrixBetween(min, max);
    }

    @Override
    public List<Produit> getProduitsEnStock() {
        return produitRepository.findByQuantiteDisponibleGreaterThan(0);
    }


    @Override
    public Produit getProduitById(int id) {
        return produitRepository.findById(id).get();
    }


}
