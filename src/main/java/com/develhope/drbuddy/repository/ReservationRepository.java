package com.develhope.drbuddy.repository;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository <Reservation, Integer> {
    List <Reservation> findByDoctor (Doctor doctor_id);
    List<Reservation> findByPatient (Patient patient_id);
}
