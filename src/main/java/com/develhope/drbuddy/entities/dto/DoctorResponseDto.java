package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.enums.RecordStatus;

public class DoctorResponseDto {

    private Integer id;
    private String firstname;
    private String lastname;
    private String telephoneNumber;
    private String email;
    private String city;
    private String address;
    private Secretary secretaryId;
    private RecordStatus recordStatus;

    public DoctorResponseDto(Integer id, String firstname, String lastname, String telephoneNumber, String email, String city, String address, Secretary secretaryId, RecordStatus recordStatus) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.email = email;
        this.city = city;
        this.address = address;
        this.secretaryId = secretaryId;
        this.recordStatus = recordStatus;
    }

    public static DoctorResponseDto fromDoctor(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getId(),
                doctor.getFirstname(),
                doctor.getLastname(),
                doctor.getTelephoneNumber(),
                doctor.getEmail(),
                doctor.getCity(),
                doctor.getAddress(),
                doctor.getSecretary(),
                doctor.getRecordStatus()
        );
    }

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

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
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

    public Secretary getSecretaryId() {
        return secretaryId;
    }

    public void setSecretaryId(Secretary secretaryId) {
        this.secretaryId = secretaryId;
    }
}

