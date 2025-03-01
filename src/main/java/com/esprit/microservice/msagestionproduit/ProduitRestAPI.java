package com.esprit.microservice.msagestionproduit;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    public class ProduitRestAPI {

        private String title = "Hello, I'm the Product Micro-Service";

        @RequestMapping("/hello")
        public String sayHello() {
            System.out.println(title);
            return title;
        }
    }


