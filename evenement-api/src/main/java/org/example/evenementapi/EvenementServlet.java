package org.example.evenementapi;

import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Persistence;
import org.example.pojo.mariadb.Evenement;
import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;


@WebServlet(name = "Evenement", value = "/evenement/*")
public class EvenementServlet extends jakarta.servlet.http.HttpServlet {
    private EntityManagerFactory emf;

    @Override
    public void init() throws ServletException {
        super.init();
        emf = Persistence.createEntityManagerFactory("gestion_association");
    }

    @Override
    public void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
        String path = req.getPathInfo();
        if (path != null) {
            String[] params = path.substring(1).split("/");
            if (params.length == 1 && "listeEvenement".equals(params[0])) {
                java.util.List<Evenement> evenements = getListEvent();
                sendJson(resp, evenements);
            } else if (params.length == 2 && "getEvenement".equals(params[0])) {
                try {
                    Long id = Long.parseLong(params[1]);
                    org.example.pojo.mariadb.Evenement evenement = getEvent(id);
                    if (evenement != null) {
                        sendJson(resp, evenement);
                    } else {
                        resp.sendError(jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND, "Événement non trouvé");
                    }
                } catch (NumberFormatException e) {
                    resp.sendError(jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST, "Format d'ID invalide");
                }
            } else {
                resp.sendError(jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST, "Requête non reconnue ou mal formée");
            }
        } else {
            resp.sendError(jakarta.servlet.http.HttpServletResponse.SC_BAD_REQUEST, "Aucun chemin d'accès spécifié");
        }
    }

    public List<Evenement> getListEvent() {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT e FROM Evenement e");
            return query.getResultList();
        } catch (jakarta.persistence.PersistenceException e) {
            // Log the exception
            return null;
        } finally {
            em.close();
        }
    }

    public Evenement getEvent(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Evenement.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Convertir le corps de la requête JSON en objet Evenement
        Evenement evenement = new Gson().fromJson(req.getReader(), Evenement.class);

        // Ajouter l'événement à la base de données
        if (addEvent(evenement)) {
            resp.setStatus(HttpServletResponse.SC_CREATED, "Événement créé");
        } else {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Impossible de créer l'événement");
        }
    }

    public Boolean addEvent(Evenement evenement) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (evenement.getId() != null) { // Vérifier si l'ID est défini
                em.merge(evenement); // Utiliser merge pour une entité existante
            } else {
                em.persist(evenement); // Utiliser persist pour une nouvelle entité
            }
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        if (path != null && path.length() > 1) {
            String idStr = path.substring(1); // Supposer que l'ID est le premier paramètre après /evenement/
            try {
                Long id = Long.parseLong(idStr);
                Evenement evenementToUpdate = new Gson().fromJson(req.getReader(), Evenement.class);
                evenementToUpdate.setId(id);
                if (updateEvent(evenementToUpdate)) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour");
                }
            } catch (NumberFormatException e) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Format d'ID invalide");
            }
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID d'événement manquant");
        }
    }

    public boolean updateEvent(Evenement evenementToUpdate) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Evenement evenementExisting = em.find(Evenement.class, evenementToUpdate.getId());
            if (evenementExisting != null) {
                // Mettre à jour uniquement les champs fournis
                if (evenementToUpdate.getNom() != null) evenementExisting.setNom(evenementToUpdate.getNom());
                if (evenementToUpdate.getDateHeureDebut() != null)
                    evenementExisting.setDateHeureDebut(evenementToUpdate.getDateHeureDebut());
                if (evenementToUpdate.getDateHeureFin() != null)
                    evenementExisting.setDateHeureFin(evenementToUpdate.getDateHeureFin());
                if (evenementToUpdate.getMaxParticipants() != 0)
                    evenementExisting.setMaxParticipants(evenementToUpdate.getMaxParticipants());
                if (evenementToUpdate.getLieu() != null) evenementExisting.setLieu(evenementToUpdate.getLieu());
                em.merge(evenementExisting);
                em.getTransaction().commit();
                return true;
            } else {
                return false; // L'événement avec cet ID n'existe pas
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if (path == null && path.length() < 1) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID d'événement manquant");
            return;
        }
        String idStr = path.substring(1);

        EntityManager em = emf.createEntityManager();
        try {
            Evenement evenementToDelete = em.find(Evenement.class, Long.parseLong(idStr));
            if (evenementToDelete != null) {
                em.getTransaction().begin();
                em.remove(evenementToDelete);
                em.getTransaction().commit();
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Événement non trouvé");
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Format d'ID invalide");
        } finally {
            em.close();
        }
    }

    private void sendJson(HttpServletResponse response, Object obj) throws IOException {
        String json = new Gson().toJson(obj);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    public void destroy() {
        emf.close();
        super.destroy();
    }
}
