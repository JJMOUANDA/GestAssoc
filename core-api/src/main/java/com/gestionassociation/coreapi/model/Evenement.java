package com.gestionassociation.coreapi.model;

public record Evenement(int id, String nom, String dateHeureDebut, String dateHeureFin, int maxParticipants, Lieu lieu) {
}
