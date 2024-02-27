package org.example.evenementapi;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.Persistence;
import org.example.pojo.mariadb.Evenement;
import com.google.gson.Gson;

import java.io.IOException;

public class EvenementServlet extends jakarta.servlet.http.HttpServlet {
  private EntityManagerFactory emf;

  @Override
  public void init() throws ServletException {
    super.init();
    emf = Persistence.createEntityManagerFactory("gestion_association");
  }

  public List<Evenement> getListEvenement() {
    EntityManager em = emf.createEntityManager();
    try {
      Query requete = em.createQuery("SELECT s FROM Evenement s");
      return (List<Evenement>) requete.getResultList();
    } catch (Exception e) {
      return null;
    }finally {
      em.close();
    }
  }

  public Boolean addEvenement(Evenement evenement) {
    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.persist(evenement);
      em.getTransaction().commit();
      return true;
    } catch (Exception e) {
      return false;
    } finally {
      em.close();
    }
  }


  @Override
  protected void doGet(jakarta.servlet.http.HttpServletRequest req, jakarta.servlet.http.HttpServletResponse resp) throws jakarta.servlet.ServletException, java.io.IOException {
    String operation = req.getParameter("operation");
    // Récuperation de la liste des evenements
    if (operation.equals("listeEvenement") || operation.equals("events")) {
      req.setAttribute("evenement", this.getListEvenement());
      resp.setContentType("application/json");
      getServletConfig().getServletContext().getRequestDispatcher("/AfficheEvenement.jsp")
          .forward(req, resp);
    }
    // Récuperation d'un evenement par son ID
    else if (operation.equals("getEvenement")) {
      String id = req.getParameter("id");
      EntityManager em = emf.createEntityManager();
      if (id != null && !id.isEmpty()) {
        try {
          int eventId = Integer.parseInt(id);

          Evenement evenement = em.find(Evenement.class, eventId);
          req.setAttribute("evenement", evenement);
          getServletConfig().getServletContext().getRequestDispatcher("/afficheEvenement.jsp")
              .forward(req, resp);
        } catch (NumberFormatException e) {
          resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Format d'ID invalide");
        }finally {
          em.close();
        }
      } else {
        resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant");
      }

    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // Convertir le corps de la requête JSON en objet Evenement
    Evenement evenement = new Gson().fromJson(req.getReader(), Evenement.class);

    // Ajouter l'événement à la base de données
    if (addEvenement(evenement)) {
      resp.setStatus(HttpServletResponse.SC_CREATED);
    } else {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Impossible de créer l'événement");
    }
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String eventId = req.getParameter("id");

    if (eventId == null || eventId.isEmpty()) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID d'événement manquant");
      return;
    }

    Evenement evenementToUpdate = new Gson().fromJson(req.getReader(), Evenement.class);
    evenementToUpdate.setId(Long.parseLong(eventId)); // Assurez-vous que l'ID est correctement défini

    EntityManager em = emf.createEntityManager();
    try {
      em.getTransaction().begin();
      em.merge(evenementToUpdate);
      em.getTransaction().commit();
      resp.setStatus(HttpServletResponse.SC_OK);
    } catch (Exception e) {
      resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erreur lors de la mise à jour de l'événement");
    } finally {
      em.close();
    }
  }


  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String eventId = req.getParameter("id");

    if (eventId == null || eventId.isEmpty()) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID d'événement manquant");
      return;
    }

    EntityManager em = emf.createEntityManager();
    try {
      Evenement evenementToDelete = em.find(Evenement.class, Long.parseLong(eventId));
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


  @Override
  public void destroy() {
    emf.close();
    super.destroy();
  }



}
