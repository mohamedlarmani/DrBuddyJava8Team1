package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(int patientId) {
        patientRepository.deleteById(patientId);
    }

    public Optional<Patient> getPatientById(int patientId){
        return Optional.of(patientRepository.getById(patientId));
    }

}