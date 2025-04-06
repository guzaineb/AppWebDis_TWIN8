package com.onsdachraoui.paiement.repository;

import com.onsdachraoui.paiement.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepo extends JpaRepository<Paiement, Long> {

}