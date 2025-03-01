package com.esprit.microservice.msagestionproduit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MsaGestionProduitApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsaGestionProduitApplication.class, args);
    }
    @Autowired
    private ProduitRepository repository;

    @Bean
    ApplicationRunner init() {
        return args -> {
            repository.save(new Produit("Laptop", "Ordinateur portable", 1200.00));
            repository.save(new Produit("Smartphone", "Téléphone mobile", 800.00));
            repository.save(new Produit("Imprimante", "Imprimante laser", 200.00));

            repository.findAll().forEach(System.out::println);
        };
    }
}