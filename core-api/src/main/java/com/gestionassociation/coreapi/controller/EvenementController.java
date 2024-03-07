package com.gestionassociation.coreapi.controller;

import com.gestionassociation.coreapi.model.Evenement;
import com.gestionassociation.coreapi.service.EvenementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EvenementController {

    private final EvenementService evenementService;

    public EvenementController(EvenementService evenementService) {
        this.evenementService = evenementService;
    }

    @GetMapping("/evenement/listeEvenement")
    public ResponseEntity<?> getListeEvenement() {
        try {
            List<Evenement> evenements = evenementService.getListeEvenement();
            return new ResponseEntity<>(evenements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/evenement/getEvenement/{id}")
    public ResponseEntity<?> getEvenement(@PathVariable int id) {
        try {
            Evenement evenement = evenementService.getEvenementById(id);
            return new ResponseEntity<>(evenement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/evenement")
    public ResponseEntity<String> addEvenement(@RequestBody Evenement evenement) {
        try {
            String response = evenementService.addEvenement(evenement);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/evenement/{id}")
    public ResponseEntity<String> editEvenement(@PathVariable int id, @RequestBody Evenement evenement) {
        try {
            String response = evenementService.editEvenement(id, evenement);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/evenement/{id}")
    public ResponseEntity<String> deleteEvenement(@PathVariable int id) {
        try {
            String response = evenementService.deleteEvenement(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }
}