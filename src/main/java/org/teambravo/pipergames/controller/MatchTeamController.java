package org.teambravo.pipergames.controller;

import org.teambravo.pipergames.entity.MatchTeam;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
public class MatchTeamController {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // Create
    public void createMatchTeam(MatchTeam matchTeam) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(matchTeam);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    // Read
    public List<MatchTeam> getAllMatches() {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        String query = "SELECT t FROM MatchTeam t";
        List<MatchTeam> teamMatches = entityManager.createQuery(query, MatchTeam.class).getResultList();
        entityManager.close();
        return teamMatches;
    }

    public Optional<MatchTeam> getMatchById(int matchId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        MatchTeam matchTeam = entityManager.find(MatchTeam.class, matchId);
        entityManager.close();
        return Optional.ofNullable(matchTeam);
    }

    // Update
    public static void updateTeamMatch(MatchTeam matchTeam) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(matchTeam);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    // Delete
    public void deleteTeamMatch(int matchId) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            MatchTeam matchTeam = entityManager.find(MatchTeam.class, matchId);
            entityManager.remove(matchTeam);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}
