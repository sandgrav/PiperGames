package org.teambravo.pipergames.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// @Entity, we want this class to have persistence in the database
@Entity
// @Table, we can rename this to suit our needs, or else Hibernate takes charge.
@Table(name = "games")
public class Game {
    // We declare where the primary key is
    @Id
    // The id will be generetad by the database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private int id;

    @Column(name = "game_name")
    private String name;

    @OneToMany(mappedBy = "game", orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public Game() {}

    public Game(String name) {
        this.name = name;
    }

    public Game(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    public Game(int id, String name, List<Player> players) {
        this.id = id;
        this.name = name;
        this.players = players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
