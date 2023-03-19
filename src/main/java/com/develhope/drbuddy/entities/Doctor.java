package com.develhope.drbuddy.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "doctor")
public class Doctor extends Person{

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

    public Doctor(String email, String city, String address, Secretary secretary) {
        this.city = city;
        this.address = address;
        this.secretary = secretary;
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
