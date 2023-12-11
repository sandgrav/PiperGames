package org.teambravo.pipergames.entity;

import javax.persistence.*;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    @Column(name = "person_first_name", length = 30, nullable = false)
    private String firstName;
    // ManyToOne,OneToOne eller OneToMany? OneToMany för att person kommer finnas i fler tabeller?
    @Column(name = "person_last_name", length = 30, nullable = false)
    private String lastName;
    @Column(name = "person_nickname", length = 60, nullable = false)
    private String nickName;
    @Column(name = "person_address", length = 50)
    private String address;
    @Column(name = "person_postal_code", length = 10) //datatyp int är automatiskt not null?
//    private int postalCode;
    private String postalCode;
    @Column(name = "person_city", length = 60)
    private String city;
    @Column(name = "person_country", length = 60)
    private String country;
    @Column(name = "person_email", length = 60)
    private String email;


    public Person() {
    }

    // Behöver konstruktorn justeras? Behöver alla värden finnas med i den?
//    public Person(int id, String firstName, String lastName, String nickName, String address, int postalCode, String city, String country, String email) {
    public Person(int id, String firstName, String lastName, String nickName, String address, String postalCode, String city, String country, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

//    public int getPostalCode() { return postalCode; }
    public String getPostalCode() {
        return postalCode;
    }

//    public void setPostalCode(int postalCode) { this.postalCode = postalCode; }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

