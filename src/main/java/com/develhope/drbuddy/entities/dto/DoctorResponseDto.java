package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.enums.RecordStatus;

public class DoctorResponseDto {

    private String firstname;
    private String lastname;
    private String telephoneNumber;
    private String email;
    private String city;
    private String address;

    public DoctorResponseDto(String firstname, String lastname, String telephoneNumber, String email, String city, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.city = city;
        this.address = address;
    }

    public static DoctorResponseDto fromDoctor(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getFirstname(),
                doctor.getLastname(),
                doctor.getTelephoneNumber(),
                doctor.getEmail(),
                doctor.getCity(),
                doctor.getAddress()
        );
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

}

