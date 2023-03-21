package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;
import com.develhope.drbuddy.entities.dto.ReservationRequestDto;
import com.develhope.drbuddy.entities.dto.ReservationResponseDto;
import com.develhope.drbuddy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/postReservation")
    public ReservationResponseDto createReservation(@RequestBody ReservationRequestDto request) {
        return reservationService.postReservation(request);
    }

    @GetMapping("getReservation/{reservationById}")
    public ReservationResponseDto getReservationById(@PathVariable int reservationId){
        return reservationService.getReservation(reservationId);
    }

    @GetMapping("/patient/{patientById}")
    public List<Reservation> getReservationsByPatient(@PathVariable int patientId) {
        Patient newPatient = new Patient();
        newPatient.setId(patientId);
        return reservationService.getReservationsByPatient(newPatient);
    }

    @GetMapping("/doctor/{doctorById}")
    public List<Reservation> getReservationsByDoctor(@PathVariable int doctorId) {
        Doctor newDoctor = new Doctor();
        newDoctor.setId(doctorId);
        return reservationService.getReservationsByDoctor(newDoctor);
    }

    @DeleteMapping("/deleteReservation/{reservationById}")
    public ReservationResponseDto deleteAppointment(@PathVariable Integer reservationId) {
        return reservationService.delete(reservationId);
    }
}