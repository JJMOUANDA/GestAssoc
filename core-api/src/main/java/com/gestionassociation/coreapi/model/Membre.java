package com.gestionassociation.coreapi.model;

import java.time.LocalDate;

public record Membre (int id, String nom, String prenom, LocalDate dateNaissance, String adresse, String mail, String motDePasse) {
}
