package com.gestionassociation.coreapi.service;

import com.gestionassociation.coreapi.model.Evenement;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
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

    public Evenement getEvenementById(int id) {
        ResponseEntity<Evenement> exchange = restTemplate.exchange(MESSAGE_API_URL + "/evenement/getEvenement/" + id, HttpMethod.GET, null, Evenement.class);
        return exchange.getBody();
    }

    public String addEvenement(Evenement evenement) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Evenement> requestEntity = new HttpEntity<>(evenement, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(MESSAGE_API_URL + "/evenement/", HttpMethod.POST, requestEntity, String.class);
        return exchange.getBody();
    }

    public String editEvenement(int id, Evenement evenement) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Evenement> requestEntity = new HttpEntity<>(evenement, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(MESSAGE_API_URL + "/evenement/" + id, HttpMethod.PUT, requestEntity, String.class);
        return exchange.getBody();
    }

    public String deleteEvenement(int id) {
        ResponseEntity<String> exchange = restTemplate.exchange(MESSAGE_API_URL + "/evenement/" + id, HttpMethod.DELETE, null, String.class);
        return exchange.getBody();
    }

}
