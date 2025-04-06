package com.onsdachraoui.client.service;

import com.onsdachraoui.client.entity.Adress;
import com.onsdachraoui.client.repository.AdressRepo;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeocodingServiceImpl implements IGeocodingService {

    private final AdressRepo adressRepo;
    private final RestTemplate restTemplate;

    @org.springframework.beans.factory.annotation.Value("${geocoding.api.key}")
    private String apiKey;

    @Override
    @Transactional
    public Adress enrichWithGeocoding(Adress adress) {
        if(adress.getCoordinates() == null) {
            Adress.Coordinates coords = fetchCoordinatesFromAPI(adress);
            adress.setCoordinates(coords);
            return adressRepo.save(adress);
        }
        return adress;
    }

    @Override
    @Transactional
    public List<Adress> batchGeocode(List<Adress> adresses) {
        return adresses.stream()
                .map(this::enrichWithGeocoding)
                .collect(Collectors.toList());
    }

    private Adress.Coordinates fetchCoordinatesFromAPI(Adress adress) {
        String fullAddress = String.format("%s %s, %s",
                adress.getHouseNumber(),
                adress.getStreet(),
                adress.getZipCode());

        String url = String.format(
                "https://api.opencagedata.com/geocode/v1/json?q=%s&key=%s",
                URLEncoder.encode(fullAddress, StandardCharsets.UTF_8),
                apiKey);

        try {
            ResponseEntity<OpenCageResponse> response = restTemplate.getForEntity(
                    url, OpenCageResponse.class);

            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                OpenCageResponse.Result result = response.getBody().getResults().get(0);
                return new Adress.Coordinates(
                        result.getGeometry().getLat(),
                        result.getGeometry().getLng());
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors du géocodage de l'adresse", e);
        }
        return null;
    }
    // Classes pour parser la réponse de l'API OpenCage
    @Getter @Setter
    private static class OpenCageResponse {
        private List<Result> results;

        @Getter @Setter
        public static class Result {
            private Geometry geometry;
        }

        @Getter
        @Setter
        public static class Geometry {
            private double lat;
            private double lng;
        }
    }
}