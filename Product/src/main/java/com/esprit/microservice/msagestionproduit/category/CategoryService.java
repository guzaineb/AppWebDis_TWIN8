package com.esprit.microservice.msagestionproduit.category;



import com.esprit.microservice.msagestionproduit.produit.Produit;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    List<Produit> getProduitsByCategory(Long categoryId);
}
