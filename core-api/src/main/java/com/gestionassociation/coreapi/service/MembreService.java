package com.gestionassociation.coreapi.service;

import com.gestionassociation.coreapi.model.Membre;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MembreService {

      private final RestTemplate restTemplate;
      private final static String MEMBRE_API_URL = "http://localhost:8082/membres";

      public MembreService(RestTemplate restTemplate) {
          this.restTemplate = restTemplate;
      }

      public List<Membre> getAllMembres() {
          ResponseEntity<List<Membre>> response = restTemplate.exchange(
                  MEMBRE_API_URL,
                  HttpMethod.GET,
                  null,
                  new ParameterizedTypeReference<>() {}
          );
          return response.getBody();
      }

      public Membre getMembreById(Long id) {
          ResponseEntity<Membre> response = restTemplate.exchange(
                  MEMBRE_API_URL + "/" + id,
                  HttpMethod.GET,
                  null,
                  Membre.class
          );
          return response.getBody();
      }

      public String saveMembre(Membre membre) {
          ResponseEntity<String> response = restTemplate.postForEntity(
                  MEMBRE_API_URL,
                  membre,
                  String.class
          );
          return response.getBody();
      }

      public String editMembre(Long id, Membre membre) {
          restTemplate.put(
                  MEMBRE_API_URL + "/" + id,
                  membre
          );
          return "Membre modifié";
      }

      public String deleteMembre(Long id) {
          restTemplate.delete(
                  MEMBRE_API_URL + "/" + id
          );
          return "Membre supprimé";
      }
}
