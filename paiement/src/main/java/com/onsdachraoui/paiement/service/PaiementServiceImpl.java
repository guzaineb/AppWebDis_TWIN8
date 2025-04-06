package com.onsdachraoui.paiement.service;

import com.onsdachraoui.paiement.entity.Paiement;
import com.onsdachraoui.paiement.repository.PaiementRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaiementServiceImpl implements IPaiementService {

    PaiementRepo paiementRepo;

    @Override
    public Paiement addPaiement(Paiement paiement) {
        return paiementRepo.save(paiement);
    }
    @Override
    public Paiement updatePaiement(Paiement paiement) {
        return paiementRepo.save(paiement);
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepo.deleteById(id);
    }

    @Override
    public Paiement getPaiementById(Long id) {
        return paiementRepo.findById(id).orElse(null);
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepo.findAll();
    }

}
