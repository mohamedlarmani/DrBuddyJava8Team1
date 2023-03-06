package com.develhope.drbuddy.repository;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Integer> {
    List<Reservation> findByDoctor (Doctor doctor_id);
    List<Reservation> findByPatient (Doctor patient_id);
}
