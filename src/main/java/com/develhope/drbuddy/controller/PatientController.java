package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        return patientService.getPatientById(patientId);
    }
}