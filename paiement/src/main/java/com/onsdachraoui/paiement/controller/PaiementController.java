package com.onsdachraoui.paiement.controller;

import com.onsdachraoui.paiement.entity.Paiement;
import com.onsdachraoui.paiement.service.IPaiementService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/paiement/paiements")
public class PaiementController {
    IPaiementService paiementService;

    @PostMapping("/add")
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @GetMapping("/get")
    public List<Paiement> getAllPaiements() {
        return paiementService.getAllPaiements();
    }

    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable Long id) {
        return paiementService.getPaiementById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePaiement(@PathVariable Long id) {
        paiementService.deletePaiement(id);
    }

    @PutMapping("/{id}")
    public Paiement updatePaiement(@RequestBody Paiement paiement, @PathVariable Long id) {
        paiement.setId(id); // Ensure the ID is set correctly for updating
        return paiementService.updatePaiement(paiement);
    }
}
