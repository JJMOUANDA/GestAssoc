package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletException;
import models.Commentaire;
import connection.Connection;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

@WebServlet(name = "Commentaire", value = "/commentaire")
public class CommentaireServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (Pattern.compile("/message-api/commentaire").matcher(uri).matches()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(convertToJson(getListCommentaire()));
        } else if (Pattern.compile("/message-api/commentaire/(\\w+)").matcher(uri).matches()) {

            String id = uri.substring(uri.lastIndexOf('/') + 1);
            ObjectId objectId = new ObjectId(id);

            Commentaire commentaire = getCommentaireById(objectId);
            String jsonCommentaire = convertToJson(commentaire);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonCommentaire);
        } else if (Pattern.compile("/message-api/commentaire/evenement/(\\w+)").matcher(uri).matches()) {

            String id = uri.substring(uri.lastIndexOf('/') + 1);
            int evenementId = Integer.parseInt(id);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(convertToJson(getCommentaireByEvenementId(evenementId)));
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int evenementId = Integer.parseInt(request.getParameter("evenementId"));
        int auteurId = Integer.parseInt(request.getParameter("auteurId"));
        String texte = request.getParameter("texte");

        if (evenementId == 0 || auteurId == 0 || texte == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Paramètres invalides !");
            return;
        }

        Commentaire nouveauCommentaire = new Commentaire(evenementId, auteurId, texte);

        MongoCollection<Commentaire> commentairesCollection = database.getCollection("commentaires", Commentaire.class);
        commentairesCollection.insertOne(nouveauCommentaire);

        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Commentaire créé avec succès !");
    }

    private String convertToJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

}
