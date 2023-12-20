package org.teambravo.pipergames.entity;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
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

    @OneToMany(
            mappedBy = "tournament",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TournamentPlayer> players = new ArrayList<>();

/*
    @OneToMany(mappedBy = "team_matches_id", orphanRemoval = true)
    private List<MatchTeam> teamMatches;
*/

    @OneToMany(mappedBy = "tournament", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<MatchSolo> soloMatches = new ArrayList<>();

    @Column(name = "date_quarterfinal")
    private Timestamp dateQuarterFinal;

    @Column(name = "date_semifinal")
    private Timestamp dateSemiFinal;

    @Column(name = "date_final")
    private Timestamp dateFinal;

    public void addPlayer(Player player, Integer round, Integer index) {
        TournamentPlayer tournamentPlayer = new TournamentPlayer(this, player, round, index);
        players.add(tournamentPlayer);
        player.getTournaments().add(tournamentPlayer);
    }

    public void removePlayer(Player player) {
        for (Iterator<TournamentPlayer> iterator = players.iterator();
             iterator.hasNext(); ) {
            TournamentPlayer tournamentPlayer = iterator.next();

            if (tournamentPlayer.getTournament().equals(this) &&
                    tournamentPlayer.getPlayer().equals(player)) {
                iterator.remove();
                tournamentPlayer.getPlayer().getTournaments().remove(tournamentPlayer);
                tournamentPlayer.setTournament(null);
                tournamentPlayer.setPlayer(null);
            }
        }
    }

    public void addMatch(MatchSolo matchSolo) {
        soloMatches.add(matchSolo);
        matchSolo.setTournament(this);
    }

    public void removeMatch(MatchSolo matchSolo) {
        for (Iterator<MatchSolo> iterator = soloMatches.iterator();
             iterator.hasNext(); ) {
            MatchSolo match = iterator.next();

            if (match.equals(matchSolo)) {
                iterator.remove();
                matchSolo.setTournament(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        return id != null && id.equals(((Tournament) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    public Tournament() {
    }

    public Tournament(Integer id, String name, Game game, List<TournamentPlayer> players, List<MatchSolo> soloMatches, Timestamp dateQuarterFinal, Timestamp dateSemiFinal, Timestamp dateFinal) {
        this.id = id;
        this.name = name;
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

