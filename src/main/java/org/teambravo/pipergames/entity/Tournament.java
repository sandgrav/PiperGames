package org.teambravo.pipergames.entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private Integer id;

    @Column(name = "tournament_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToMany
//    @JoinTable(
//            name = "tournament_players",
//            joinColumns = @JoinColumn(name = "tournament_id"),
//            inverseJoinColumns = @JoinColumn(name = "player_id"))
    private List<Player> players;

/*
    @OneToMany(mappedBy = "team_matches_id", orphanRemoval = true)
    private List<MatchTeam> teamMatches;
*/

    @OneToMany(mappedBy = "tournament", orphanRemoval = true)
    private List<MatchSolo> soloMatches;

    @Column(name = "date_quarterfinal")
    private Timestamp dateQuarterFinal;

    @Column(name = "date_semifinal")
    private Timestamp dateSemiFinal;

    @Column(name = "date_final")
    private Timestamp dateFinal;

    public Tournament() {
    }

    public Tournament(Integer id, Game game, List<Player> players, List<MatchSolo> soloMatches, Timestamp dateQuarterFinal, Timestamp dateSemiFinal, Timestamp dateFinal) {
        this.id = id;
        this.game = game;
        this.players = players;
        this.soloMatches = soloMatches;
        this.dateQuarterFinal = dateQuarterFinal;
        this.dateSemiFinal = dateSemiFinal;
        this.dateFinal = dateFinal;
    }

    public Tournament(Game game, List<Player> players, List<MatchSolo> soloMatches, Timestamp dateQuarterFinal, Timestamp dateSemiFinal, Timestamp dateFinal) {
        this.game = game;
        this.players = players;
        this.soloMatches = soloMatches;
        this.dateQuarterFinal = dateQuarterFinal;
        this.dateSemiFinal = dateSemiFinal;
        this.dateFinal = dateFinal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

/*
    public List<MatchTeam> getTeamMatches() {
        return teamMatches;
    }

    public void setTeamMatches(List<MatchTeam> teamMatches) {
        this.teamMatches = teamMatches;
    }
*/

    public List<MatchSolo> getSoloMatches() {
        return soloMatches;
    }

    public void setSoloMatches(List<MatchSolo> soloMatches) {
        this.soloMatches = soloMatches;
    }

    public Timestamp getDateQuarterFinal() {
        return dateQuarterFinal;
    }

    public void setDateQuarterFinal(Timestamp dateQuarterFinal) {
        this.dateQuarterFinal = dateQuarterFinal;
    }

    public Timestamp getDateSemiFinal() {
        return dateSemiFinal;
    }

    public void setDateSemiFinal(Timestamp dateSemiFinal) {
        this.dateSemiFinal = dateSemiFinal;
    }

    public Timestamp getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(Timestamp dateFinal) {
        this.dateFinal = dateFinal;
    }
}

