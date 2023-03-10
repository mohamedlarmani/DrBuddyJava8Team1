package com.develhope.drbuddy.entities;

import com.develhope.drbuddy.utilities.Auditable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Person extends Auditable {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Integer id;
    @Size (max = 45)
    @NotNull
    @Column(name = "firstname", nullable = false, length = 45)
    private String firstname;
    @Size(max = 45)
    @NotNull
    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;
    @Size(max = 15)
    @NotNull
    @Column(name = "telephone_number", nullable = false, length = 15)
    private String telephoneNumber;

    @Size(max = 45)
    @NotNull
    @Column(name = "email", nullable = false, length = 45)
    private String email;

    public Person(Integer id, String firstname, String lastname, String telephoneNumber, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    public Person(){};

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
