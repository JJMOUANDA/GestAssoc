package com.gestionassociation.evenementapi;

import com.gestionassociation.evenementapi.pojo.Evenement;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;

public class TestJPA {

  public static void main(String[] argv) throws Exception {

    // charge le gestionnaire d'entités lié à l'unité de persistance "SportsPU"
    EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("gestion_association");
    EntityManager em = emf.createEntityManager();
    System.out.println("Eve chargée");

    // récupère le events d'identifiant 1, affiche son intitulé et ses disciplines
    int cle = 1;
    Evenement events = em.find(Evenement.class, cle);
    System.out.println("L'Evenement numero " + cle + " est " + events.getNom());
  }

}
