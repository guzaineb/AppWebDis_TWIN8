package com.onsdachraoui.client.controller;

import com.onsdachraoui.client.entity.Adress;
import com.onsdachraoui.client.service.IAdressService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/adress/adresses")
public class AdressRestController {

    private final IAdressService adressService;

    @PostMapping("/add")
    public Adress addAdress(@RequestBody Adress adress) {
        return adressService.addAdress(adress);
    }

    @GetMapping("/get")
    public List<Adress> getAllAdresses() {
        return adressService.getAllAdresses();
    }

    @GetMapping("/{id}")
    public Adress getAdressById(@PathVariable Long id) {
        return adressService.getAdressById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAdress(@PathVariable Long id) {
        adressService.deleteAdress(id);
    }

    @PutMapping("/{id}")
    public Adress updateAdress(@RequestBody Adress adress, @PathVariable Long id) {
        return adressService.updateAdress(adress);
    }
}
