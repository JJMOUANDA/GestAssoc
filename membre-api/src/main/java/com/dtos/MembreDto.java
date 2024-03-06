package com.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MembreDto {
	
	private Long id;
	private String nom;
	private String prenom;
	private LocalDate dateNaissance;
	private String adresse;
	private String mail;
	private String motDePasse;
	
}
