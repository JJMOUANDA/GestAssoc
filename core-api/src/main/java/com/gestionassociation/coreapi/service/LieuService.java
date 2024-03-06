package com.gestionassociation.coreapi.service;

import com.gestionassociation.coreapi.model.Lieu;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LieuService {

    private final RestTemplate restTemplate;
    private final static String LIEU_API_URL = "http://localhost:8080/lieux";

    public LieuService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Lieu> getListeLieux() {
        ResponseEntity<List<Lieu>> response = restTemplate.exchange(
                LIEU_API_URL + "/lieux",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    public Lieu getLieuById(String id) {
        ResponseEntity<Lieu> response = restTemplate.exchange(
                LIEU_API_URL + "/lieu/" + id,
                HttpMethod.GET,
                null,
                Lieu.class
        );
        return response.getBody();
    }

    public String addLieu(Lieu lieu) {
        ResponseEntity<String> response = restTemplate.postForEntity(
                LIEU_API_URL + "/lieu",
                lieu,
                String.class
        );
        return response.getBody();
    }


    public String editLieu(String id, Lieu lieu) {
        restTemplate.put(
                LIEU_API_URL + "/lieu/" + id,
                lieu
        );
        return "Lieu modifié";
    }

    public String deleteLieu(String id) {
        restTemplate.delete(
                LIEU_API_URL + "/lieu/" + id
        );
        return "Lieu supprimé";
    }


}
