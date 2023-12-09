package org.teambravo.pipergames.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private int playerId;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "team_id1")
    private TeamClass team;

    @OneToMany(mappedBy = "player1", cascade = CascadeType.ALL)
    private List<MatchSoloPlayer> matchesAsPlayer1 = new ArrayList<>();

    @OneToMany(mappedBy = "player2", cascade = CascadeType.ALL)
    private List<MatchSoloPlayer> matchesAsPlayer2 = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }



    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TeamClass getTeam() {
        return team;
    }

    public void setTeam(TeamClass team) {
        this.team = team;
    }

    public List<MatchSoloPlayer> getMatchesAsPlayer1() {
        return matchesAsPlayer1;
    }

    public void setMatchesAsPlayer1(List<MatchSoloPlayer> matchesAsPlayer1) {
        this.matchesAsPlayer1 = matchesAsPlayer1;
    }

    public List<MatchSoloPlayer> getMatchesAsPlayer2() {
        return matchesAsPlayer2;
    }

    public void setMatchesAsPlayer2(List<MatchSoloPlayer> matchesAsPlayer2) {
        this.matchesAsPlayer2 = matchesAsPlayer2;
    }

}

