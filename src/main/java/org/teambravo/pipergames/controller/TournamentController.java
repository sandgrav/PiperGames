    package org.teambravo.pipergames.controller;

    import org.teambravo.pipergames.entity.Player;
    import org.teambravo.pipergames.entity.Tournament;

    import javax.persistence.*;
    import java.util.ArrayList;
    import java.util.List;

    public class TournamentController {

        // The value "hibernate" at the end of the row is a pointer of which settings in persistence.xml to use.
        public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

        // CREATE
        public boolean save(Tournament tournament) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.persist(tournament);
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

        // READ
        public List<Tournament> getAll() {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                List<Tournament> listToReturn = new ArrayList<>(entityManager.createQuery("SELECT t FROM Tournament t", Tournament.class).getResultList());
                transaction.commit();
                return listToReturn;
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

        public Tournament getById(int id) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                Tournament tournamentToReturn = entityManager.find(Tournament.class, id);
                transaction.commit();
                return tournamentToReturn;
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

        public List<Tournament> getByGameId(int id) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                List<Tournament> listToReturn = new ArrayList<>(entityManager.
                        createQuery("SELECT t FROM Tournament t WHERE t.game.id = :game_id", Tournament.class)
                        .setParameter("game_id", id).getResultList());
                transaction.commit();
                return listToReturn;
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

        public boolean update(Tournament tournament){
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.merge(tournament);
                transaction.commit();
                return true;
            } catch (Exception e){
                if(transaction != null){
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
            }
            return false;
        }

        public boolean deleteById(int id) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.remove(entityManager.contains(id) ? id : entityManager.merge(id));
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

        public boolean delete(Tournament tournament) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.remove(entityManager.contains(tournament) ? tournament : entityManager.merge(tournament));
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
    }