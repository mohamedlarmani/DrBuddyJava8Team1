package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Secretary;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DoctorRequestDto {

    @NotNull
    @Size(max = 45)
    private String firstname;

    @NotNull
    @Size(max = 45)
    private String lastname;

    @NotNull
    @Size(max = 15)
    private String telephoneNumber;

    @NotNull
    @Size(max = 45)
    private String email;

    @NotNull
    @Size(max = 60)
    private String city;

    @NotNull
    @Size(max = 100)
    private String address;

    private Secretary secretary;

    public DoctorRequestDto() {}

    public DoctorRequestDto(String firstname, String lastname, String telephoneNumber, String email, String city, String address, Secretary secretary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.city = city;
        this.address = address;
        this.secretary = secretary;
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
