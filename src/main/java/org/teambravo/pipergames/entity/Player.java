package org.teambravo.pipergames.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "players")
public class Player {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_person_id")
    private Integer id;

    @OneToOne(orphanRemoval = true)
    @MapsId
    private Person person;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "player1", orphanRemoval = true)  //, fetch = FetchType.EAGER,orphanRemoval = true)
    private List<MatchSolo> matchesAsPlayer1 = new ArrayList<>();

    @OneToMany(mappedBy = "player2", orphanRemoval = true)  //, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MatchSolo> matchesAsPlayer2 = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(
            mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<TournamentPlayer> tournaments = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        return id != null && id.equals(((Player) o).getPerson().getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Player() {
    }

    public Player(Integer playerId, Person person, Team team, List<MatchSolo> matchesAsPlayer1, List<MatchSolo> matchesAsPlayer2, Game game, List<TournamentPlayer> tournaments) {
        this.id = playerId;
        this.person = person;
        this.team = team;
        this.matchesAsPlayer1 = matchesAsPlayer1;
        this.matchesAsPlayer2 = matchesAsPlayer2;
        this.game = game;
        this.tournaments = tournaments;
    }

    public Player(Person person, Team team, List<MatchSolo> matchesAsPlayer1, List<MatchSolo> matchesAsPlayer2, Game game, List<TournamentPlayer> tournaments) {
        this.person = person;
        this.team = team;
        this.matchesAsPlayer1 = matchesAsPlayer1;
        this.matchesAsPlayer2 = matchesAsPlayer2;
        this.game = game;
        this.tournaments = tournaments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<MatchSolo> getMatchesAsPlayer1() {
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
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<TournamentPlayer> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentPlayer> tournaments) {
        this.tournaments = tournaments;
    }
}

