package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;
import com.develhope.drbuddy.repository.PatientRepository;
import com.develhope.drbuddy.repository.ReservationRepository;
import com.develhope.drbuddy.service.PatientService;
import com.develhope.drbuddy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    private PatientService patientService;



    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.savePatient(patient);
    }


    @GetMapping("/{patientId}")
    public Optional<Patient> getPatientById(@PathVariable int patientId){
        Optional<Patient> optionalPatient = patientService.getPatientById(patientId);
        if (optionalPatient.isPresent()) {
            return optionalPatient;
        } else {
            throw new EntityNotFoundException("Patient with id " + patientId + " not found");
        }
    }


}