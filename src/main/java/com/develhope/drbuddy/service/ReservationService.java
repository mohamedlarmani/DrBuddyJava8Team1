package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;
import com.develhope.drbuddy.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Integer reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    public Optional<Reservation> getReservationById(int reservationId){
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);
        if (optionalReservation.isPresent()) {
            return optionalReservation;
        } else {
            throw new EntityNotFoundException("Reservation with id " + reservationId + " not found");
        }
    }

    public List<Reservation> getReservationsByDoctor(Doctor doctor_id) {
        return reservationRepository.findBydoctor(doctor_id);
    }

    public List<Reservation> getReservationsByPatient(Patient patient_id) {
        return reservationRepository.findBypatient(patient_id);
    }
}
