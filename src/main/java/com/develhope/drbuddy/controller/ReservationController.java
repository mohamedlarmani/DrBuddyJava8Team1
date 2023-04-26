package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Reservation;
import com.develhope.drbuddy.entities.dto.ReservationRequestDto;
import com.develhope.drbuddy.entities.dto.ReservationResponseDto;
import com.develhope.drbuddy.service.ReservationService;
import it.pasqualecavallo.studentsmaterial.authorization_framework.filter.AuthenticationContext;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.PublicEndpoint;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.RoleSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RoleSecurity("ROLE_USER")
    @PostMapping("/postReservation")
    public ReservationResponseDto createReservation(@RequestBody ReservationRequestDto request) {
        return reservationService.postReservation(request);
    }

    @GetMapping("getReservation/{reservationId}")
    public ReservationResponseDto getReservationById(@PathVariable int reservationId){
        return reservationService.getReservation(reservationId);
    }

    @RoleSecurity("ROLE_USER")
    @GetMapping("/patient")
    public List<ReservationResponseDto> getReservationsByPatient() {
        return reservationService.getReservationsByPatient(AuthenticationContext.get().getUsername());
    }

    @RoleSecurity("ROLE_DOCTOR")
    @GetMapping("/doctor")
    public List<ReservationResponseDto> getReservationsByDoctor() {
        return reservationService.getReservationsByDoctor(AuthenticationContext.get().getUsername());
    }

    @DeleteMapping("/deleteReservation/{reservationById}")
    public ReservationResponseDto deleteAppointment(@PathVariable Integer reservationId) {
        return reservationService.delete(reservationId);
    }
}