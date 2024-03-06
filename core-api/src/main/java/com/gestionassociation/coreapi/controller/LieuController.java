package com.gestionassociation.coreapi.controller;

import com.gestionassociation.coreapi.model.Lieu;
import com.gestionassociation.coreapi.service.LieuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lieu")
public class LieuController {

    private final LieuService lieuService;

    public LieuController(LieuService lieuService) {
        this.lieuService = lieuService;
    }

    @GetMapping("/lieux")
    public ResponseEntity<List<Lieu>> getListeLieux() {
        return ResponseEntity.ok(lieuService.getListeLieux());
    }

    @GetMapping("/lieu/{id}")
    public ResponseEntity<Lieu> getLieu(@PathVariable String id) {
        return ResponseEntity.ok(lieuService.getLieuById(id));
    }

    @PostMapping("/lieu")
    public ResponseEntity<String> addLieu(@RequestBody Lieu lieu) {
        return ResponseEntity.ok(lieuService.addLieu(lieu));
    }

    @PutMapping("/lieu/{id}")
    public ResponseEntity<String> editLieu(@PathVariable String id, @RequestBody Lieu lieu) {
        return ResponseEntity.ok(lieuService.editLieu(id, lieu));
    }


    @DeleteMapping("/lieu/{id}")
    public ResponseEntity<String> deleteLieu(@PathVariable String id) {
        return ResponseEntity.ok(lieuService.deleteLieu(id));
    }


}
