package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public PatientService() {
    }

    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(int patientId) {
        patientRepository.deleteById(patientId);
    }

    public Optional<Patient> getPatientById(int patientId){
        Optional<Patient> optionalPatient = patientRepository.findById(patientId);
        if (optionalPatient.isPresent()) {
            return optionalPatient;
        } else {
            throw new EntityNotFoundException("Patient with id " + patientId + " not found");
        }
    }


}