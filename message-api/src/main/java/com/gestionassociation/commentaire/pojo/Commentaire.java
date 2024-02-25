package com.gestionassociation.commentaire.pojo;

import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Commentaire {

    private ObjectId id;
    private int evenementId;

    private int auteurId;

    private String texte;

    private Date date;

    public Commentaire() {
    }

    public Commentaire(int evenementId, int auteurId, String texte) {
        this.evenementId = evenementId;
        this.auteurId = auteurId;
        this.texte = texte;
        this.date = new Date();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(int evenementId) {
        this.evenementId = evenementId;
    }

    public int getAuteurId() {
        return auteurId;
    }

    public void setAuteurId(int auteurId) {
        this.auteurId = auteurId;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentaire that = (Commentaire) o;
        return evenementId == that.evenementId && auteurId == that.auteurId && Objects.equals(id, that.id) && Objects.equals(texte, that.texte) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, evenementId, auteurId, texte, date);
    }
}
