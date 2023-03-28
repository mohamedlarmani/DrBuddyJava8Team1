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

    public PatientResponseDto postPatient(PatientRequestDto request) {
        return patientEntityToResponse(patientRepository.save(patientRequestToEntity(request)));
    }

    public PatientResponseDto getPatient(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(RuntimeException::new);
        return patientEntityToResponse(patient);
    }

    public PatientResponseDto putPatient(int id, PatientRequestDto request) {
        Patient patient = patientRepository.findById(id).orElseThrow(RuntimeException::new);
        patientRequestToEntity(request, patient);
        return patientEntityToResponse(patientRepository.save(patient));
    }

    public PatientResponseDto delete(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(RuntimeException::new);
        patient.setRecordStatus(RecordStatus.D);
        return patientEntityToResponse(patientRepository.save(patient));
    }

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
            patient.setRecordStatus(RecordStatus.A);
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

    private Patient patientRequestToEntity(PatientRequestDto request) {
        Patient patient = new Patient();
        return patientRequestToEntity(request, patient);
    }

    private Patient patientRequestToEntity(PatientRequestDto request, Patient patient){
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setTelephoneNumber(request.getTelephoneNumber());
        patient.setEmail(request.getEmail());
        return patient;
    }

    private PatientResponseDto patientEntityToResponse(Patient patient){
        PatientResponseDto response = new PatientResponseDto();
        response.setFirstname(patient.getFirstname());
        response.setLastname(patient.getLastname());
        response.setTelephoneNumber(patient.getTelephoneNumber());
        response.setEmail(patient.getEmail());
        return response;
    }

    private Patient patientRequestToEntityRegistration(RegistrationRequestDto request){
        Patient patient = new Patient();
        patient.setEmail(request.getEmail());
        patient.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        System.out.println(bCryptPasswordEncoder.encode(request.getPassword()));
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setTelephoneNumber(request.getTelephoneNumber());
        patient.setRecordStatus(RecordStatus.I);
        patient.setActivationCode(StringUtility.generateRandomString(6));
        return patient;
    }

    private RegistrationResponseDto patientEntityToResponseRegistration(){
        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setStatus(BaseResponse.Status.OK);
        return response;
    }

}

