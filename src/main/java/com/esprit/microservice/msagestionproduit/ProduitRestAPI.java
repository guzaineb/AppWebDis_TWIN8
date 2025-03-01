package com.esprit.microservice.msagestionproduit;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
    public class ProduitRestAPI {
    @Autowired
    private ProduitService produitService;

        private String title = "Hello, I'm the Product Micro-Service";

        @RequestMapping("/hello")
        public String sayHello() {
            System.out.println(title);
            return title;
        }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produit> createProduit(@RequestBody Produit produit) {
        return new ResponseEntity<>(produitService.addProduit(produit), HttpStatus.OK);
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


}


