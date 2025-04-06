package com.onsdachraoui.client.controller;

import com.onsdachraoui.client.entity.Adress;
import com.onsdachraoui.client.service.IAdressService;
import com.onsdachraoui.client.service.IGeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// GeocodingController.java
@RestController
@RequestMapping("/api/geocoding")
@RequiredArgsConstructor
public class GeocodingController {

    private final IGeocodingService geocodingService;
    private final IAdressService adressService;

    @PostMapping("/{id}/geocode")
    public ResponseEntity<Adress> geocodeAdress(@PathVariable Long id) {
        Adress adress = adressService.getAdressById(id);
        if(adress == null) {
            return ResponseEntity.notFound().build();
        }
        Adress result = geocodingService.enrichWithGeocoding(adress);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/batch-geocode")
    public ResponseEntity<List<Adress>> batchGeocode(@RequestBody List<Long> adressIds) {
        List<Adress> adresses = adressIds.stream()
                .map(adressService::getAdressById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<Adress> results = geocodingService.batchGeocode(adresses);
        return ResponseEntity.ok(results);
    }
}
