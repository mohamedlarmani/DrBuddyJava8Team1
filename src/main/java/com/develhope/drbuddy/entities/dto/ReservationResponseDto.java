package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;

import java.time.LocalDateTime;

public class ReservationResponseDto {

    private LocalDateTime dateReservation;
    private PatientResponseDto patient;
    private DoctorResponseDto doctor;

    public ReservationResponseDto(){}

    public ReservationResponseDto(LocalDateTime dateReservation, DoctorResponseDto doctor, PatientResponseDto patient) {
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

    public PatientResponseDto getPatient() {
        return patient;
    }

    public void setPatient(PatientResponseDto patient) {
        this.patient = patient;
    }

    public DoctorResponseDto getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorResponseDto doctor) {
        this.doctor = doctor;
    }
}
