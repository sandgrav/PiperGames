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
    private TeamClass teamClass1;

    @ManyToOne
    @JoinColumn(name = "team2_id", referencedColumnName = "team_id")
    private TeamClass teamClass2;

    @Column(name  = "date")
    private int date;


    public MatchTeam() {
    }

    public MatchTeam(int id, TeamClass player1, TeamClass player2, int date) {
        this.id = id;
        this.teamClass1 = teamClass1;
        this.teamClass2 = teamClass2;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TeamClass getTeamClass1() {
        return teamClass1;
    }

    public void setTeamClass1(TeamClass teamClass1) {
        this.teamClass1 = teamClass1;
    }

    public TeamClass getTeamclass2() {
        return teamClass2;
    }

    public void setTeamClass2(TeamClass teamClass2) {
        this.teamClass2 = teamClass2;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
