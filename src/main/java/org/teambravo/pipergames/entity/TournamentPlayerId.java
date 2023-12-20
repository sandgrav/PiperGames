package org.teambravo.pipergames.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TournamentPlayerId implements Serializable {
    @Column(name = "tournament_id")
    private Integer tournamentId;
    @Column(name = "player_id")
    private Integer playerId;

    public TournamentPlayerId() {}

    public TournamentPlayerId(Integer tournamentId, Integer playerId) {
        this.tournamentId = tournamentId;
        this.playerId = playerId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        TournamentPlayerId that = (TournamentPlayerId) o;
        return Objects.equals(tournamentId, that.tournamentId) &&
                Objects.equals(playerId, that.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tournamentId, playerId);
    }
}
