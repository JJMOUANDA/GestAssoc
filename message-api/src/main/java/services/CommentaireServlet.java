package services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletException;
import models.Commentaire;
import connection.Connection;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
            response.setStatus(HttpServletResponse.SC_OK);

        } else if (Pattern.compile("/message-api/commentaire/(\\w+)").matcher(uri).matches()) {

            String id = uri.substring(uri.lastIndexOf('/') + 1);
            ObjectId objectId = new ObjectId(id);

            Commentaire commentaire = getCommentaireById(objectId);
            String jsonCommentaire = convertToJson(commentaire);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonCommentaire);
            response.setStatus(HttpServletResponse.SC_OK);

        } else if (Pattern.compile("/message-api/commentaire/evenement/(\\w+)").matcher(uri).matches()) {

            String id = uri.substring(uri.lastIndexOf('/') + 1);
            int evenementId = Integer.parseInt(id);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(convertToJson(getCommentaireByEvenementId(evenementId)));
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Requête invalide");
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

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("Commentaire créé avec succès !");
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (Pattern.compile("/message-api/commentaire/(\\w+)").matcher(uri).matches()) {
            String id = uri.substring(uri.lastIndexOf('/') + 1);
            ObjectId objectId = new ObjectId(id);
            String texte = request.getParameter("texte");

            Commentaire commentaire = getCommentaireById(objectId);
            if (commentaire != null) {
                commentaire.setTexte(texte);
                commentaire.setDate(new Date());

                MongoCollection<Commentaire> commentairesCollection = database.getCollection("commentaires", Commentaire.class);
                commentairesCollection.replaceOne(eq("_id", objectId), commentaire);

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Commentaire mis à jour avec succès");
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Commentaire non trouvé");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Requête invalide");
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (Pattern.compile("/message-api/commentaire/(\\w+)").matcher(uri).matches()) {
            String id = uri.substring(uri.lastIndexOf('/') + 1);
            ObjectId objectId = new ObjectId(id);

            Commentaire commentaire = getCommentaireById(objectId);
            if (commentaire != null) {
                MongoCollection<Commentaire> commentairesCollection = database.getCollection("commentaires", Commentaire.class);
                commentairesCollection.deleteOne(eq("_id", objectId));

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Commentaire supprimé avec succès");
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Commentaire non trouvé");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Requête invalide");
        }
    }

    private String convertToJson(Object object) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(object);
    }

}
