package com.develhope.drbuddy.entities;

import com.develhope.drbuddy.enums.RecordStatus;
import com.develhope.drbuddy.utilities.Auditable;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Person extends Auditable {

    @Id
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

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "record_status", nullable = false, length = 1)
    private RecordStatus recordStatus;

    @Column(name = "password", nullable = false, length = 65)
    private String password;

    @Column(name = "activation_code", nullable = false, length = 6)
    private String activationCode;

    @Column(name = "active", nullable = false, length = 1)
    private boolean active;

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

    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
