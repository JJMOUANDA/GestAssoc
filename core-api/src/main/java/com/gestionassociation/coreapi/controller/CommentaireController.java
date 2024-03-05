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
    public ResponseEntity<List<Commentaire>> getListeCommentaire() {
        List<Commentaire> commentaires = commentaireService.getListeCommentaire();
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }

    @GetMapping("/commentaire/{id}")
    public ResponseEntity<Commentaire> getCommentaire(@PathVariable String id) {
        Commentaire commentaire = commentaireService.getCommentaireById(id);
        return new ResponseEntity<>(commentaire, HttpStatus.OK);
    }

    @GetMapping("/commentaire/evenement/{id}")
    public ResponseEntity<List<Commentaire>> getCommentaireByEvenementId(@PathVariable int id) {
        List<Commentaire> commentaires = commentaireService.getCommentaireByEvenementId(id);
        return new ResponseEntity<>(commentaires, HttpStatus.OK);
    }

    @PutMapping("/commentaire/{id}")
    public ResponseEntity<String> editCommentaire(@PathVariable String id, @RequestParam String texte) {
        String response = commentaireService.editCommentaire(id, texte);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/commentaire/{id}")
    public ResponseEntity<String> deleteCommentaire(@PathVariable String id) {
        String response = commentaireService.deleteCommentaire(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/commentaire")
    public ResponseEntity<String> addCommentaire(@RequestParam int evenementId, @RequestParam int auteurId, @RequestParam String texte) {
        String response = commentaireService.addCommentaire(evenementId, auteurId, texte);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}