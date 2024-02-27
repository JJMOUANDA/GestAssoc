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
	@Column(name = "dateNaissance")
	private LocalDate dateNaissance;
	private String adresse;
	private String mail;
	@Column(name = "motDePasse")
	private String motDePasse;

	
}
