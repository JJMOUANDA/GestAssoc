package com.gestionassociation.coreapi.controller;

import com.gestionassociation.coreapi.model.Evenement;
import com.gestionassociation.coreapi.service.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evenement")
public class EvenementController {

    private final EvenementService evenementService;

    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/listeEvenement")
    public ResponseEntity<?> getListeEvenement() {
        try {
            List<Evenement> evenements = evenementService.getListeEvenement();
            return new ResponseEntity<>(evenements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }
}