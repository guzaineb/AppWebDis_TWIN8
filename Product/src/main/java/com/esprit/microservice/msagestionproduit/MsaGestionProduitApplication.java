package com.esprit.microservice.msagestionproduit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableDiscoveryClient
public class MsaGestionProduitApplication {


    public static void main(String[] args) {
        SpringApplication.run(MsaGestionProduitApplication.class, args);
    }

}