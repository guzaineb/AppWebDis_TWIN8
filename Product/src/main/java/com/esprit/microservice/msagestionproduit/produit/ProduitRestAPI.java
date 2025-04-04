package com.esprit.microservice.msagestionproduit.produit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/produits")
public class ProduitRestAPI {

    @Autowired
    private ProduitService produitService;

    private String title = "Hello, I'm the Product Micro-Service";

    @GetMapping("/hello")
    public String sayHello() {
        return title;
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.addProduit(produit), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Produit> updateProduit(@PathVariable(value = "id") int id,
                                                 @RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.updateProduit(id, produit), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteProduit(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(produitService.deleteProduit(id), HttpStatus.OK);
    }

    // API pour récupérer tous les produits
    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getAllProduits() {
        return new ResponseEntity<>(produitService.getAllProduits(), HttpStatus.OK);
    }

    // API pour rechercher un produit par nom
    @GetMapping("/nom/{nom}")
    public ResponseEntity<Produit> getProduitByNom(@PathVariable String nom) {
        Produit produit = produitService.getProduitByNom(nom);
        return produit != null ? new ResponseEntity<>(produit, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // API avancée 1 : Récupérer les produits dans une fourchette de prix
    @GetMapping("/prix")
    public ResponseEntity<List<Produit>> getProduitsByPrixRange(@RequestParam double min, @RequestParam double max) {
        return new ResponseEntity<>(produitService.getProduitsByPrixRange(min, max), HttpStatus.OK);
    }

    // API avancée 2 : Récupérer uniquement les produits en stock
    @GetMapping("/en-stock")
    public ResponseEntity<List<Produit>> getProduitsEnStock() {
        return new ResponseEntity<>(produitService.getProduitsEnStock(), HttpStatus.OK);
    }
}
