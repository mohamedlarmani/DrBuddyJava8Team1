package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

public class ReservationRequestDto {

    @NotNull
    @Column(name = "date_reservation", nullable = false)
    private LocalDateTime dateReservation;
    private Patient patient;
    private Doctor doctor;

    public ReservationRequestDto (){};

    public ReservationRequestDto(LocalDateTime dateReservation, Patient patient, Doctor doctor) {
        this.dateReservation = dateReservation;
        this.patient = patient;
        this.doctor = doctor;
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
