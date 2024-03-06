package com.gestionassociation.coreapi.controller;

import com.gestionassociation.coreapi.model.Membre;
import com.gestionassociation.coreapi.service.MembreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membres")
public class MembreController {

      private final MembreService membreService;

      public MembreController(MembreService membreService) {
          this.membreService = membreService;
      }

      @GetMapping("/membre")
      public ResponseEntity<List<Membre>> getAllMembres() {
          return ResponseEntity.ok(membreService.getAllMembres());
      }

      @GetMapping("/{id}")
      public ResponseEntity<Membre> getMembreById(@PathVariable Long id) {
          return ResponseEntity.ok(membreService.getMembreById(id));
      }

      @PostMapping
      public ResponseEntity<String> addMembre(@RequestBody Membre membre) {
          return ResponseEntity.ok(membreService.saveMembre(membre));
      }

      @PutMapping("/{id}")
      public ResponseEntity<String> editMembre(@PathVariable Long id, @RequestBody Membre membre) {
          return ResponseEntity.ok(membreService.editMembre(id, membre));
      }
}
