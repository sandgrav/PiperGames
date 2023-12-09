package org.teambravo.pipergames.controller;

import org.teambravo.pipergames.entity.MatchSoloPlayer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
public class MatchSoloPlayerController {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // Create
    public void createMatchSoloPlayer(MatchSoloPlayer soloMatch) {
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
    public List<MatchSoloPlayer> getAllMatches() {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT m FROM MatchSoloPlayer m";
        List<MatchSoloPlayer> matches = em.createQuery(query, MatchSoloPlayer.class).getResultList();
        em.close();
        return matches;
    }

    public Optional<MatchSoloPlayer> getMatchById(int matchId) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        MatchSoloPlayer soloMatch = em.find(MatchSoloPlayer.class, matchId);
        em.close();
        return Optional.ofNullable(soloMatch);
    }

    // Update
    public void updateMatchSoloPlayer(MatchSoloPlayer soloMatch) {
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
            MatchSoloPlayer soloMatch = em.find(MatchSoloPlayer.class, matchId);
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
