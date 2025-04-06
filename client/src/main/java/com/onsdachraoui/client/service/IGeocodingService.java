package com.onsdachraoui.client.service;

import com.onsdachraoui.client.entity.Adress;

import java.util.List;

// IGeocodingService.java
public interface IGeocodingService {
    Adress enrichWithGeocoding(Adress adress);
    List<Adress> batchGeocode(List<Adress> adresses);
}