package org.teambravo.pipergames.controller;

import org.teambravo.pipergames.entity.MatchSolo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
public class MatchSoloController {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // Create
    public void createMatchSoloPlayer(MatchSolo soloMatch) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.persist(soloMatch);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Read
    public List<MatchSolo> getAllMatches() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT m FROM MatchSolo m";
        List<MatchSolo> matches = em.createQuery(query, MatchSolo.class).getResultList();
        em.close();
        return matches;
    }

    public Optional<MatchSolo> getMatchById(int matchId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        MatchSolo soloMatch = em.find(MatchSolo.class, matchId);
        em.close();
        return Optional.ofNullable(soloMatch);
    }

    // Update
    public void updateMatchSoloPlayer(MatchSolo soloMatch) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            em.merge(soloMatch);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // Delete
    public void deleteMatchSoloPlayer(int matchId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;

        try {
            et = em.getTransaction();
            et.begin();
            MatchSolo soloMatch = em.find(MatchSolo.class, matchId);
            em.remove(soloMatch);
            et.commit();
        } catch (Exception e) {
            if (et != null) {
                et.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
