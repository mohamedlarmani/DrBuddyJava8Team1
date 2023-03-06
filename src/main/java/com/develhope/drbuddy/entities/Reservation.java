package com.develhope.drbuddy.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReservation", nullable = false)
    private int id;

    @NotNull
    @Column(name = "date_reservation", nullable = false)
    private LocalDateTime dateReservation;


    @ManyToOne
    private Patient patient;


    @ManyToOne
    private Doctor doctor;



    public Reservation(){}

    public Reservation(int id, LocalDateTime dateReservation, Patient patientIdPatient, Doctor doctorIddoctor) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.patient = patientIdPatient;
        this.doctor = doctorIddoctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}