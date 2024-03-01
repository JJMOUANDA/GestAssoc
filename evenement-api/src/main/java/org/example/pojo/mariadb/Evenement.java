package org.example.pojo.mariadb;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@Table(name = "evenement")
public class Evenement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private Date dateHeureDebut;

    private Date dateHeureFin;

    private int maxParticipants;

    @ManyToOne
    @JoinColumn(name = "lieuId")
    private Lieu lieu;
    public Evenement() {
    }

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

    public Date getDateHeureDebut() {
        return dateHeureDebut;
    }

    public void setDateHeureDebut(Date dateHeureDebut) {
        this.dateHeureDebut = dateHeureDebut;
    }

    public Date getDateHeureFin() {
        return dateHeureFin;
    }

    public void setDateHeureFin(Date dateHeureFin) {
        this.dateHeureFin = dateHeureFin;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Lieu getLieu() {
        return lieu;
    }

    public void setLieu(Lieu lieu) {
        this.lieu = lieu;
    }
}
