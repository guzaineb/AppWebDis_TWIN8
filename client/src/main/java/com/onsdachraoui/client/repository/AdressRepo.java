package com.onsdachraoui.client.repository;

import com.onsdachraoui.client.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdressRepo extends JpaRepository<Adress, Long> {
    Optional<Adress> findByStreetAndHouseNumberAndZipCode(
            String street, String houseNumber, String zipCode);

}
