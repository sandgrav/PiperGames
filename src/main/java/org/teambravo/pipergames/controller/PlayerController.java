    package org.teambravo.pipergames.controller;

    import org.teambravo.pipergames.entity.Player;
    import org.teambravo.pipergames.entity.Staff;

    import javax.persistence.*;
    import java.util.ArrayList;
    import java.util.List;

    public class PlayerController {

        // The value "hibernate" at the end of the row is a pointer of which settings in persistence.xml to use.
        public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("hibernate");

        // CREATE
        public boolean savePlayer(Player player) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.persist(player.getPerson());
                player.setPlayerId(player.getPerson().getId());
                entityManager.persist(player);
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
        public List<Player> getAllPlayer(boolean printOut) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                List<Player> listToReturn = new ArrayList<>(entityManager.createQuery("SELECT p FROM Player p", Player.class).getResultList());
                transaction.commit();
                if (printOut) {
                    for (Player player :
                            listToReturn) {
                        System.out.println(player.getPlayerId() + ". " + player.getTeam() + ". " + player.getPerson());
                    }
                }
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

        public Player getPlayerById(int playerId) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                Player playerToReturn = entityManager.find(Player.class, playerId);
                transaction.commit();
                return playerToReturn;
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

        public boolean update(Player player){
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.merge(player.getPerson());
                player.setPlayerId(player.getPerson().getId());
                entityManager.merge(player);
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

        public boolean deletePlayerById(int playerId) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.remove(entityManager.contains(playerId) ? playerId : entityManager.merge(playerId));
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

        public boolean deletePlayer(Player player) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                entityManager.remove(entityManager.contains(player) ? player : entityManager.merge(player));
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

        public boolean deletePlayerByName(String playerName) {
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();

                TypedQuery<Player> query = entityManager.createQuery(
                        "SELECT p FROM Player p WHERE " +
                                "p.person.firstName = :name OR " +
                                "p.person.lastName = :name OR " +
                                "p.person.nickName = :name",
                        Player.class
                );
                query.setParameter("name", playerName);

                List<Player> playersToDelete = query.getResultList();

                for (Player player : playersToDelete) {
                    entityManager.remove(player);
                }

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

        /*
        public boolean addTeamToPlayer(int teamId, int playerId){
            EntityManager entityManager = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction transaction = null;
            Player player;
            try {
                transaction = entityManager.getTransaction();
                transaction.begin();
                Optional<Player> possiblyAPlayer = Optional.ofNullable(entityManager.find(Player.class,playerId));
                Optional<Team> possiblyATeam = Optional.ofNullable(entityManager.find(Team.class, teamId));
                if(possiblyAPlayer.isPresent() && possiblyATeam.isPresent()){
                    System.out.println("Båda finns");
                    Team teamClass = possiblyATeam.get();
                    player = possiblyAPlayer.get();
                    player.addTeamClass(teamClass);
                }
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
        }*/
    }