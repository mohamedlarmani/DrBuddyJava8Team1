package com.develhope.drbuddy.entities.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonRequestDto {

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

    public PersonRequestDto(){}

    public PersonRequestDto(String firstname, String lastname, String telephoneNumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
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
