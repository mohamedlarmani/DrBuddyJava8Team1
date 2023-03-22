package com.develhope.drbuddy.service;


import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.dto.*;
import com.develhope.drbuddy.enums.RecordStatus;
import com.develhope.drbuddy.exception.InvalidActivationCodeException;
import com.develhope.drbuddy.exception.UserNotFoundException;
import com.develhope.drbuddy.repository.PatientRepository;
import com.develhope.drbuddy.utilities.EmailSender;
import com.develhope.drbuddy.utilities.StringUtility;
import it.pasqualecavallo.studentsmaterial.authorization_framework.utils.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public RegistrationResponseDto register(RegistrationRequestDto request) {
        Patient patient = patientRequestToEntityRegistration(request);
        patientRepository.save(patient);
        emailSender.sendRegistrationEmail(patient);
        return patientEntityToResponseRegistration();
    }


    public ActivateResponseDto activate(ActivateRequestDto request) {
        Optional<Patient> oPatient = patientRepository.findByEmail(request.getEmail());
        Patient patient = oPatient.orElseThrow(UserNotFoundException::new);
        if(request.getActivationCode().equals(patient.getActivationCode())) {
            patient.setActive(true);
            patient.setActivationCode("null");
            patientRepository.save(patient);
            ActivateResponseDto response = new ActivateResponseDto();
            response.setStatus(BaseResponse.Status.OK);
            response.setFirstName(patient.getFirstname());
            return response;
        } else {
            throw new InvalidActivationCodeException();
        }
    }

    private Patient patientRequestToEntityRegistration(RegistrationRequestDto request){
        Patient patient = new Patient();
        patient.setEmail(request.getEmail());
        patient.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        System.out.println(bCryptPasswordEncoder.encode(request.getPassword()));
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setTelephoneNumber(request.getTelephoneNumber());
        patient.setRecordStatus(RecordStatus.A);
        patient.setActivationCode(StringUtility.generateRandomString(6));
        patient.setActive(false);
        return patient;
    }

    private RegistrationResponseDto patientEntityToResponseRegistration(){
        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setStatus(BaseResponse.Status.OK);
        return response;
    }
}

