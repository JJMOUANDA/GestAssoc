package com.gestionassociation.commentaire.servlet;

import com.gestionassociation.commentaire.util.CommentaireDAO;
import com.gestionassociation.commentaire.util.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "Evenement", value = "/commentaire/evenement")
public class EvenementServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final CommentaireDAO commentaireDAO = new CommentaireDAO();
    private final JsonUtil jsonUtil = new JsonUtil();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (Pattern.compile("/message-api/commentaire/evenement/(\\w+)").matcher(uri).matches()) {

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
}