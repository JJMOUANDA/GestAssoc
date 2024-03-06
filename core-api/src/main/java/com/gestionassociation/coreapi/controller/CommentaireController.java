package com.gestionassociation.coreapi.controller;

import com.gestionassociation.coreapi.model.Commentaire;
import com.gestionassociation.coreapi.service.CommentaireService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class CommentaireController {

    private final CommentaireService commentaireService;

    public CommentaireController(CommentaireService commentaireService) {
        this.commentaireService = commentaireService;
    }

    @GetMapping("/commentaire")
    public ResponseEntity<?> getListeCommentaire() {
        try {
            List<Commentaire> commentaires = commentaireService.getListeCommentaire();
            return new ResponseEntity<>(commentaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/commentaire/{id}")
    public ResponseEntity<?> getCommentaire(@PathVariable String id) {
        try {
            Commentaire commentaire = commentaireService.getCommentaireById(id);
            return new ResponseEntity<>(commentaire, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/commentaire/evenement/{id}")
    public ResponseEntity<?> getCommentaireByEvenementId(@PathVariable int id) {
        try {
            List<Commentaire> commentaires = commentaireService.getCommentaireByEvenementId(id);
            return new ResponseEntity<>(commentaires, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/commentaire/{id}")
    public ResponseEntity<String> editCommentaire(@PathVariable String id, @RequestParam String texte) {
        try {
            String response = commentaireService.editCommentaire(id, texte);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/commentaire/{id}")
    public ResponseEntity<String> deleteCommentaire(@PathVariable String id) {
        try {
            String response = commentaireService.deleteCommentaire(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/commentaire")
    public ResponseEntity<String> addCommentaire(@RequestParam int evenementId, @RequestParam int auteurId, @RequestParam String texte) {
        try {
            String response = commentaireService.addCommentaire(evenementId, auteurId, texte);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Une erreur s'est produite", HttpStatus.BAD_REQUEST);
        }
    }
}