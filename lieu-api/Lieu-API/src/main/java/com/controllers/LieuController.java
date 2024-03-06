package com.controllers;

import com.dtos.LieuDto;
import com.services.impl.LieuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lieux")
public class LieuController {

    private final LieuServiceImpl lieuService;

    public LieuController(LieuServiceImpl lieuService) {
        this.lieuService = lieuService;
    }

    @GetMapping
    public ResponseEntity<List<LieuDto>> getAllLieux() {
        List<LieuDto> lieux = lieuService.findAll();
        return new ResponseEntity<>(lieux, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LieuDto> getLieuById(@PathVariable Long id) {
        LieuDto lieu = lieuService.findById(id);
        return new ResponseEntity<>(lieu, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LieuDto> createLieu(@RequestBody LieuDto lieuDto) {
        LieuDto newLieu = lieuService.save(lieuDto);
        return new ResponseEntity<>(newLieu, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LieuDto> updateLieu(@PathVariable Long id, @RequestBody LieuDto lieuDto) {
        lieuDto.setId(id);
        LieuDto updatedLieu = lieuService.save(lieuDto);
        return new ResponseEntity<>(updatedLieu, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLieu(@PathVariable Long id) {
        lieuService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
