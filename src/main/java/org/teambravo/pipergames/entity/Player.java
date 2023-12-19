package org.teambravo.pipergames.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Integer playerId;

//    @ManyToOne
//    @JoinColumn(name = "person_id")
    @OneToOne(orphanRemoval = true)
    @MapsId
    private Person person;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    // @OneToMany(mappedBy = "player1", orphanRemoval = true)
    // private List<MatchSolo> matchesAsPlayer1 = new ArrayList<>();

    // @OneToMany(mappedBy = "player2", orphanRemoval = true)
    // private List<MatchSolo> matchesAsPlayer2 = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToMany //(mappedBy = "tournament_players")
    private List<Tournament> tournaments;

    public Player() {
    }

    public Player(int playerId, Person person, Team team, List<MatchSolo> matchesAsPlayer1, List<MatchSolo> matchesAsPlayer2, Game game, List<Tournament> tournaments) {
        this.playerId = playerId;
        this.person = person;
        this.team = team;
        // this.matchesAsPlayer1 = matchesAsPlayer1;
        // this.matchesAsPlayer2 = matchesAsPlayer2;
        this.game = game;
        this.tournaments = tournaments;
    }

    public Player(Person person, Team team, List<MatchSolo> matchesAsPlayer1, List<MatchSolo> matchesAsPlayer2, Game game, List<Tournament> tournaments) {
        this.person = person;
        this.team = team;
        // this.matchesAsPlayer1 = matchesAsPlayer1;
        // this.matchesAsPlayer2 = matchesAsPlayer2;
        this.game = game;
        this.tournaments = tournaments;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    /*public List<MatchSolo> getMatchesAsPlayer1() {
        return matchesAsPlayer1;
    }

    public void setMatchesAsPlayer1(List<MatchSolo> matchesAsPlayer1) {
        this.matchesAsPlayer1 = matchesAsPlayer1;
    }

    public List<MatchSolo> getMatchesAsPlayer2() {
        return matchesAsPlayer2;
    }

    public void setMatchesAsPlayer2(List<MatchSolo> matchesAsPlayer2) {
        this.matchesAsPlayer2 = matchesAsPlayer2;
    } */

}

