package com.gestionassociation.coreapi.service;

import com.gestionassociation.coreapi.model.Evenement;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("evenementService")

public class EvenementService {

    private final static String MESSAGE_API_URL = "http://localhost:8082/evenement-api";

    private final RestTemplate restTemplate;

    public EvenementService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Evenement> getListeEvenement() {
        ResponseEntity<List<Evenement>> exchange = restTemplate.exchange(MESSAGE_API_URL + "/evenement/listeEvenement", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return exchange.getBody();
    }
}
