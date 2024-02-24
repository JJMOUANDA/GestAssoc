package models;

import org.bson.types.ObjectId;

import java.util.Objects;

public class Commentaire {

    private ObjectId id;
    private int evenementId;

    private int membresId;

    private String textCommentaire;

    private String date;

    public Commentaire() {
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

    public int getMembresId() {
        return membresId;
    }

    public void setMembresId(int membresId) {
        this.membresId = membresId;
    }

    public String getTextCommentaire() {
        return textCommentaire;
    }

    public void setTextCommentaire(String textCommentaire) {
        this.textCommentaire = textCommentaire;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commentaire that = (Commentaire) o;
        return evenementId == that.evenementId && membresId == that.membresId && Objects.equals(id, that.id) && Objects.equals(textCommentaire, that.textCommentaire) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, evenementId, membresId, textCommentaire, date);
    }
}
