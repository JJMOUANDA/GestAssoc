package com.gestionassociation.coreapi.controller;

import com.gestionassociation.coreapi.model.Commentaire;
import com.gestionassociation.coreapi.service.CommentaireService;
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
}