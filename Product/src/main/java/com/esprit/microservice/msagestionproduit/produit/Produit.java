package com.esprit.microservice.msagestionproduit.produit;

import com.esprit.microservice.msagestionproduit.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String description;
    private double quantiteDisponible;  // Correction de la convention
    private double prix;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("produits")
    private Category category;
}
