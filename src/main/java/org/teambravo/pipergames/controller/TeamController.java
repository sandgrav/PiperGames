package org.teambravo.pipergames.controller;

import org.teambravo.pipergames.entity.Team;

import javax.persistence.*;
import java.util.List;

public class TeamController {

    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

    // CRUD

    //CREATE

    public boolean saveTeam(Team team) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(team);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return false;
    }

    //READ
    public List<Team> getAllTeams (boolean printOut){
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            List<Team> teamResultList = entityManager.createQuery("FROM Team", Team.class).getResultList();
            // teamClassToReturn.addAll(teamResultList.getResultList());
            transaction.commit();
            if (printOut) {
                for (Team team :
                        teamResultList) {
                    System.out.println(team.getName() + ". " + team.getName());

                }
            }
            return  teamResultList;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    //Get team by one singular name
    public Team getTeamByOneName(String teamName) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            List<Team> teamResultList = entityManager
                    .createQuery("FROM Team t WHERE t.name = :teamName", Team.class)
                    .setParameter("teamName", teamName)
                    .getResultList();

            transaction.commit();

            if (!teamResultList.isEmpty()) {
                return teamResultList.get(0); // Return the first team found with the given name
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
        return null;
    }

    public boolean updateTeam(Team updatedTeam) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            Team teamToUpdate = entityManager.find(Team.class, updatedTeam.getId());
            if (teamToUpdate != null) {
                teamToUpdate.setName(updatedTeam.getName()); // Update the name of the retrieved team
                entityManager.merge(teamToUpdate);
                transaction.commit();
                return true;
            }

            transaction.rollback();
            return false;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            entityManager.close();
        }
    }


    public boolean deleteTeamByName(String name) {
        EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            TypedQuery<Team> query = entityManager.createQuery("SELECT t FROM Team t WHERE t.name = :name", Team.class);
            query.setParameter("name", name);
            Team teamToDelete = query.getSingleResult();

            entityManager.remove(teamToDelete);
            transaction.commit();
            return true;

        } catch (NoResultException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;

        } finally {
            entityManager.close();
        }
    }


}




