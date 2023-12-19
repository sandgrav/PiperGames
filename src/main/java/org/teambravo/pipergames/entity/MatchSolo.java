package org.teambravo.pipergames.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solo_matches")
public class MatchSolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solo_match_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "player1_id")   //, referencedColumnName = "player_id")
    private Player player1;

    @ManyToOne
    @JoinColumn(name = "player2_id")   //, referencedColumnName = "player_id")
    private Player player2;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Player winner;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public MatchSolo() {
    }

    public MatchSolo(int id, Player player1, Player player2, LocalDateTime date) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.date = date;
        this.winner = winner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) { this.player2 = player2; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }
}
