package org.teambravo.pipergames.entity;


import javax.persistence.*;

@Entity
@Table(name = "Staff")
public class StaffClass {
    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public StaffClass() {
    }

    public StaffClass(int id, Person person) {
        this.id = id;
        this.person = person;
    }
    
    // Getters och setters
    public int getStaff_id() {
        return id;
    }

    public void setStaff_id(int staff_id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}



