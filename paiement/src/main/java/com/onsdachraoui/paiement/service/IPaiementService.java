package com.onsdachraoui.paiement.service;

import com.onsdachraoui.paiement.entity.Paiement;
import java.util.List;

public interface IPaiementService {
    Paiement addPaiement(Paiement paiement);
    void deletePaiement(Long id);
    Paiement getPaiementById(Long id);
    List<Paiement> getAllPaiements();
    Paiement updatePaiement(Paiement paiement);

}
