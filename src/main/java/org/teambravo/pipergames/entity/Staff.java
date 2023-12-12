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

    public Staff() {
    }

    public Staff(int id, Person person) {
        this.id = id;
        this.person = person;
    }

    public Staff(Person person) {
        this.person = person;
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
}



