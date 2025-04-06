package com.onsdachraoui.client.service;

import com.onsdachraoui.client.entity.Adress;
import java.util.List;

public interface IAdressService {
    Adress addAdress(Adress adress);
    void deleteAdress(Long id);
    Adress getAdressById(Long id);
    List<Adress> getAllAdresses();
    Adress updateAdress(Adress adress);
}
