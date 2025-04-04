package com.esprit.microservice.msagestionproduit.category;

import com.esprit.microservice.msagestionproduit.produit.Produit;
import com.esprit.microservice.msagestionproduit.produit.ProduitRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.microservice.msagestionproduit.category.CategoryService;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.orElseThrow(() -> new RuntimeException("Category not found with id " + id));
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Produit> getProduitsByCategory(Long categoryId) {
        Category category = getCategoryById(categoryId);
        return category.getProduits();
    }
}