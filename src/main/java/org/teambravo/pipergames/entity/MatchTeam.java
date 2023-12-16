package org.teambravo.pipergames.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_matches")


public class MatchTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_matches_id")
    private int id;


    @ManyToOne
    @JoinColumn(name = "team1_id", referencedColumnName = "team_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", referencedColumnName = "team_id")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Team winner;

    @Column(name  = "date")
    private LocalDateTime date;


    public MatchTeam() {
    }

    public MatchTeam(Team team1, Team team2, Team winner, LocalDateTime date) {
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.date = date;
    }

    public MatchTeam(int id, Team team1, Team team2, Team winner, LocalDateTime date) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }
}
