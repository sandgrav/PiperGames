package org.teambravo.pipergames.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournament_player")
public class TournamentPlayer {
    @EmbeddedId
    private TournamentPlayerId id;
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("tournamentId")
    private Tournament tournament;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("playerId")
    private Player player;

    @Column(name = "round")
    private Integer round;

    @Column(name = "player_index")
    private Integer index;

    public TournamentPlayer() {}

    public TournamentPlayer(Tournament tournament, Player player, Integer round, Integer index) {
        this.tournament = tournament;
        this.player = player;
        this.round = round;
        this.index = index;
        this.id = new TournamentPlayerId(tournament.getId(), player.getId());
    }

    public TournamentPlayer(Tournament tournament, Player player) {
        this.tournament = tournament;
        this.player = player;
        this.id = new TournamentPlayerId(tournament.getId(), player.getId());
    }

    public TournamentPlayerId getId() {
        return id;
    }

    public void setId(TournamentPlayerId id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        TournamentPlayer that = (TournamentPlayer) o;
        return Objects.equals(tournament, that.tournament) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournament, player);
    }
}
