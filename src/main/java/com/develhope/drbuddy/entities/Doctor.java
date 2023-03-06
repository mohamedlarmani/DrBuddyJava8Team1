package com.develhope.drbuddy.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doctor", nullable = false)
    private int id;

    @Size(max = 45)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 45)
    private String firstname;

    @Size(max = 45)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Size(max = 15)
    @NotNull
    @Column(name = "telephone_number", nullable = false, length = 15)
    private String telephoneNumber;

    @Size(max = 60)
    @NotNull
    @Column(name = "city", nullable = false, length = 60)
    private String city;

    @Size(max = 100)
    @NotNull
    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @OneToOne
    private Secretary secretary;

    public Doctor(){}

    public Doctor(int id, String firstname, String lastname, String email, String telephoneNumber, String city, String address, Secretary secretary) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.telephoneNumber = telephoneNumber;
        this.city = city;
        this.address = address;
        this.secretary = secretary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }

}
