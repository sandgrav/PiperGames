package org.teambravo.pipergames.entity;


import javax.persistence.*;

@Entity
@Table(name = "Staff")
public class Staff {
    // Primary key
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "person_id")
    @OneToOne
    @MapsId
    private Person person;

    @Column(name = "logged_in")
    private Boolean loggedIn;

    public Staff() {
    }

    public Staff(int id, Person person) {
        this.id = id;
        this.person = person;
    }

    public Staff(Person person, boolean loggedIn) {
        this.person = person;
        this.loggedIn = loggedIn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isLoggedIn() {
        return Boolean.TRUE.equals((loggedIn));
    }

    public void setLoggedIn(Boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
