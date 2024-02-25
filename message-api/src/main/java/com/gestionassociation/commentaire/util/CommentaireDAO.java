package com.gestionassociation.commentaire.util;

import com.gestionassociation.commentaire.pojo.Commentaire;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class CommentaireDAO {

    private final MongoDatabase database = Connection.getInstance().getDatabase();

    public List<Commentaire> getListCommentaire() {
        MongoCollection<Commentaire> commentaires = database.getCollection("commentaires", Commentaire.class);
        List<Commentaire> commentairesList = new ArrayList<>();

        commentaires.find().into(commentairesList);
        return commentairesList;
    }

    public Commentaire getCommentaireById(ObjectId id) {
        MongoCollection<Commentaire> commentaires = database.getCollection("commentaires", Commentaire.class);
        return commentaires.find(eq("_id", id)).first();
    }

    public List<Commentaire> getCommentaireByEvenementId(int id) {
        MongoCollection<Commentaire> commentaires = database.getCollection("commentaires", Commentaire.class);
        List<Commentaire> commentairesList = new ArrayList<>();

        commentaires.find(eq("evenementId", id)).into(commentairesList);
        return commentairesList;
    }
}
