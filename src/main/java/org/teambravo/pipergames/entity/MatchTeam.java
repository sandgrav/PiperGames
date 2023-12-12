package org.teambravo.pipergames.entity;

import javax.persistence.*;

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

    @Column(name  = "date")
    private int date;


    public MatchTeam() {
    }

    public MatchTeam(int id, Team player1, Team player2, int date) {
        this.id = id;
        this.team1 = team1;
        this.team2 = team2;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeamClass1() {
        return team1;
    }

    public void setTeamClass1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeamclass2() {
        return team2;
    }

    public void setTeamClass2(Team team2) {
        this.team2 = team2;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
