package com.entities;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
public class Membre {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	@Column(name = "date_naissance")
	private LocalDate dateNaissance;
	private String adresse;
	private String mail;
	@Column(name = "mot_de_passe")
	private String motDePasse;

	
}
