package com.gestionassociation.coreapi.service;

import com.gestionassociation.coreapi.model.Commentaire;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("commentaireService")
public class CommentaireService {

    private final static String MESSAGE_API_URL = "http://localhost:8081/message-api";

    private final RestTemplate restTemplate;

    public CommentaireService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Commentaire> getListeCommentaire() {
        ResponseEntity<List<Commentaire>> exchange = restTemplate.exchange(MESSAGE_API_URL + "/commentaire", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return exchange.getBody();
    }

    public Commentaire getCommentaireById(String id) {
        ResponseEntity<Commentaire> exchange = restTemplate.exchange(MESSAGE_API_URL + "/commentaire/" + id, HttpMethod.GET, null, Commentaire.class);
        return exchange.getBody();
    }

    public List<Commentaire> getCommentaireByEvenementId(int id) {
        ResponseEntity<List<Commentaire>> exchange = restTemplate.exchange(MESSAGE_API_URL + "/commentaire/evenement/" + id, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });
        return exchange.getBody();
    }

    public String editCommentaire(String id, String texte) {
        ResponseEntity<String> exchange = restTemplate.exchange(MESSAGE_API_URL + "/commentaire/" + id + "?texte=" + texte, HttpMethod.PUT, null, String.class);
        return exchange.getBody();
    }

    public String deleteCommentaire(String id) {
        ResponseEntity<String> exchange = restTemplate.exchange(MESSAGE_API_URL + "/commentaire/" + id, HttpMethod.DELETE, null, String.class);
        return exchange.getBody();
    }

    public String addCommentaire(int evenementId, int auteurId, String texte) {

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("evenementId", String.valueOf(evenementId));
        parameters.add("auteurId", String.valueOf(auteurId));
        parameters.add("texte", texte);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> exchange = restTemplate.exchange(MESSAGE_API_URL + "/commentaire", HttpMethod.POST, requestEntity, String.class);
        return exchange.getBody();
    }
}
