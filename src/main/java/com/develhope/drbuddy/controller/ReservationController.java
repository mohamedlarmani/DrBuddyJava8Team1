package com.develhope.drbuddy.controller;


import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;
import com.develhope.drbuddy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.saveReservation(reservation);
    }

    @DeleteMapping("/{reservationId}")
    public void deleteAppointment(@PathVariable Integer reservationId) {
        reservationService.deleteReservation(reservationId);
    }

    @GetMapping("/{reservationId}")
    public Optional<Reservation> getReservationById(@PathVariable int reservationId){
        Optional<Reservation> optionalReservation = reservationService.getReservationById(reservationId);
        if (optionalReservation.isPresent()) {
            return optionalReservation;
        } else {
            throw new EntityNotFoundException("Reservation with id " + reservationId + " not found");
        }
    }

    @GetMapping("/patient/{patientId}")
    public List<Reservation> getReservationsByPatient(@PathVariable int patientId) {
        Patient newPatient = new Patient();
        newPatient.setId(patientId);
        return reservationService.getReservationsByPatient(newPatient);
    }


    @GetMapping("/doctor/{doctorId}")
    public List<Reservation> getReservationsByDoctor(@PathVariable int doctorId) {
        Doctor newDoctor = new Doctor();
        newDoctor.setId(doctorId);
        return reservationService.getReservationsByDoctor(newDoctor);
    }
}