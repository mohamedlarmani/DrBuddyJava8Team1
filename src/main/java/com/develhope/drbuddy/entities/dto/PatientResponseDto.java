package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Patient;

public class PatientResponseDto {

    private String firstname;
    private String lastname;
    private String telephoneNumber;
    private String email;

    public PatientResponseDto(String firstname, String lastname, String telephoneNumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
    }

    public static PatientResponseDto Patient(Patient patient) {
        return new PatientResponseDto(
                patient.getFirstname(),
                patient.getLastname(),
                patient.getTelephoneNumber(),
                patient.getEmail());
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

