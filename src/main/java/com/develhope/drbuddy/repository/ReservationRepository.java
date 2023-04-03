package com.develhope.drbuddy.repository;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Integer> {
    List<Reservation> findBydoctor (Doctor doctor_id);
    List<Reservation> findBypatient (Patient patient_id);

    List<Reservation> findBydateReservation(LocalDateTime dateReservation);
}
