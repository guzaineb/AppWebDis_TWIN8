package com.esprit.microservice.msagestionproduit.produit;

import java.util.List;

public interface ImpProduitService {
    Produit addProduit(Produit produit);
    Produit updateProduit(int id, Produit newProduit);
    String deleteProduit(int id);
    List<Produit> getAllProduits(); // Récupérer tous les produits
    List<Produit> getProduitByNom(String nom); // Recherche par nom
    List<Produit> getProduitsByPrixRange(double min, double max); // Recherche avancée par intervalle de prix
    List<Produit> getProduitsEnStock(); // Recherche avancée : produits en stock uniquement
Produit getProduitById(int id);
}
