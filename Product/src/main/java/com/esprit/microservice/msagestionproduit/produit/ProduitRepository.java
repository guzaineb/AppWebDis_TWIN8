package com.esprit.microservice.msagestionproduit.produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProduitRepository extends JpaRepository<Produit, Integer> {
    Optional<Produit> findByNom(String nom);
    List<Produit> findByPrixBetween(double min, double max);
    List<Produit> findByQuantiteDisponibleGreaterThan(double quantity);
}
