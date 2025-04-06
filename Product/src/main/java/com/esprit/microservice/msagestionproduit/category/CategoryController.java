package com.esprit.microservice.msagestionproduit.category;


import com.esprit.microservice.msagestionproduit.produit.Produit;
import com.esprit.microservice.msagestionproduit.produit.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/api/categories")

public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @GetMapping(value = "/all-categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        if (categories.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(categories);
    }
    @Transactional
    @PostMapping(value = "/add-category")
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @GetMapping("/{id}/produits")
    public List<Produit> getProduitsByCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElseThrow();
        return category.getProduits();
    }
}
