

package org.teambravo.pipergames.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Teams")

public class TeamClass {
    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private int id;

    @Column(name = "team_name", length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "TeamClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    public TeamClass() {

    }

    public TeamClass(String teamName) {

    }

    public int getTeam_id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public void setTeam_id(int team_id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public TeamClass(int id, String name, Game game) {
        this.id = id;
        this.name = name;
        this.game = game;
    }

    public TeamClass(TeamClass teamClass) {
    }
}



