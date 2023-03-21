package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;

import java.time.LocalDateTime;

public class ReservationResponseDto {

    private LocalDateTime dateReservation;
    private Patient patient;
    private Doctor doctor;

    public ReservationResponseDto(){}

    public ReservationResponseDto(LocalDateTime dateReservation, Doctor doctor, Patient patient) {
        this.dateReservation = dateReservation;
        this.doctor = doctor;
        this.patient = patient;
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
