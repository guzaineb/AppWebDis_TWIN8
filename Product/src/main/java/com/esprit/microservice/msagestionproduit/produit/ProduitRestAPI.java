package com.esprit.microservice.msagestionproduit.produit;

import com.esprit.microservice.msagestionproduit.category.Category;
import com.esprit.microservice.msagestionproduit.category.CategoryRepository;
import com.esprit.microservice.msagestionproduit.category.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/produits")
public class ProduitRestAPI {

    @Autowired
    private ProduitService produitService;

    private String title = "Hello, I'm the Product Micro-Service";
    @Autowired
    private CategoryServiceImpl categoryServiceImpl;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/hello")
    public String sayHello() {
        return title;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produit> createProduit(
            @RequestParam("nom") String nom,
            @RequestParam("prix") double prix,
            @RequestParam("description") String description,
            @RequestParam("images") MultipartFile[] images,
            @RequestParam("categoryId") Integer categoryId // ajout du paramètre pour la catégorie
    ) {
        try {
            String uploadDir = "uploads/";
            List<String> imagePaths = new ArrayList<>();

            // Créer le dossier si ce n'est pas déjà fait
            File uploadPath = new File(uploadDir);
            if (!uploadPath.exists()) {
                uploadPath.mkdirs();
            }

            // Pour chaque image, on les copie dans le dossier d'upload
            for (MultipartFile image : images) {
                String fileName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                imagePaths.add(filePath.toString());
            }

            // Créer l'objet Produit
            Produit produit = new Produit();
            produit.setNom(nom);
            produit.setPrix(prix);
            produit.setDescription(description);
            produit.setImageUrls(imagePaths); // Enregistrer les chemins des images

            // Ajouter la catégorie (assurez-vous que la catégorie existe déjà)
            Category category = categoryRepository.findById(categoryId); // Tu dois implémenter cette méthode
            produit.setCategory(category);

            // Sauvegarder le produit
            Produit savedProduit = produitService.addProduit(produit);

            // Retourner la réponse avec l'objet produit créé
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PutMapping(value = "update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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
    public ResponseEntity<List<Produit>> getProduitByNom(@PathVariable String nom) {
        List<Produit> produits = produitService.getProduitByNom(nom);
        if (produits == null || produits.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(produits); // 200 OK
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
    @GetMapping("/{id}")
    public Produit getProduitById( @PathVariable (value = "id")  int id) {
return produitService.getProduitById(id);
    }


}
