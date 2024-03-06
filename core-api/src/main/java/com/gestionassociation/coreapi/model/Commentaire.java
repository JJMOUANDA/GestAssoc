package com.gestionassociation.coreapi.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public record Commentaire(@Id String id, int evenementId, int auteurId, String texte, String date) {
}
