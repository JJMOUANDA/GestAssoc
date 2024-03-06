package com.entities;

import javax.persistence.*;

@Entity
public class Lieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;
    private Integer capacite_accueil;

    // Getters et Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Integer getCapacite_accueil() {
        return capacite_accueil;
    }

    public void setCapacite_accueil(Integer capacite_accueil) {
        this.capacite_accueil = capacite_accueil;
    }
}
