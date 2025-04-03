package com.esprit.microservice.msagestionproduit;

public interface ImpProduitService  {
    public Produit addProduit(Produit produit);
    public Produit updateProduit(int id, Produit newProduit);
    public String deleteProduit(int id);

}
