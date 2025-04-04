package com.onsdachraoui.client.service;

import com.onsdachraoui.client.entity.Adress;
import com.onsdachraoui.client.repository.AdressRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdressServiceImpl implements IAdressService {

    private final AdressRepo adressRepo;

    @Override
    public Adress addAdress(Adress adress) {
        return adressRepo.save(adress);
    }

    @Override
    public Adress updateAdress(Adress adress) {
        return adressRepo.save(adress);
    }

    @Override
    public void deleteAdress(Long id) {
        adressRepo.deleteById(id);
    }

    @Override
    public Adress getAdressById(Long id) {
        return adressRepo.findById(id).orElse(null);
    }

    @Override
    public List<Adress> getAllAdresses() {
        return adressRepo.findAll();
    }

}
