package com.gestionassociation.commentaire;

import com.gestionassociation.commentaire.pojo.Commentaire;
import com.gestionassociation.commentaire.util.CommentaireDAO;
import com.gestionassociation.commentaire.util.Connection;
import com.gestionassociation.commentaire.util.JsonUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@WebServlet(name = "Commentaire", value = "/commentaire")
public class CommentaireServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final MongoDatabase database = Connection.getInstance().getDatabase();

    private final CommentaireDAO commentaireDAO = new CommentaireDAO();
    private final JsonUtil jsonUtil = new JsonUtil();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (Pattern.compile("/message-api/commentaire").matcher(uri).matches()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonUtil.convertToJson(commentaireDAO.getListCommentaire()));
            response.setStatus(HttpServletResponse.SC_OK);

        } else if (Pattern.compile("/message-api/commentaire/(\\w+)").matcher(uri).matches()) {

            String id = uri.substring(uri.lastIndexOf('/') + 1);
            ObjectId objectId = new ObjectId(id);

            Commentaire commentaire = commentaireDAO.getCommentaireById(objectId);
            String jsonCommentaire = jsonUtil.convertToJson(commentaire);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonCommentaire);
            response.setStatus(HttpServletResponse.SC_OK);

        } else if (Pattern.compile("/message-api/commentaire/evenement/(\\w+)").matcher(uri).matches()) {

            String id = uri.substring(uri.lastIndexOf('/') + 1);
            int evenementId = Integer.parseInt(id);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonUtil.convertToJson(commentaireDAO.getCommentaireByEvenementId(evenementId)));
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

            Commentaire commentaire = commentaireDAO.getCommentaireById(objectId);
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

            Commentaire commentaire = commentaireDAO.getCommentaireById(objectId);
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
}