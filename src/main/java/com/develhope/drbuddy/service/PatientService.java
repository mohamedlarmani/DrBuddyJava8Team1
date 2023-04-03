package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.dto.*;
import com.develhope.drbuddy.enums.RecordStatus;
import com.develhope.drbuddy.exception.InvalidActivationCodeException;
import com.develhope.drbuddy.exception.UserNotFoundException;
import com.develhope.drbuddy.repository.PatientRepository;
import com.develhope.drbuddy.notifications.EmailSender;
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

    /**
     * This method creates a new patient and saves it to the patient repository.
     * @param request The patient request DTO containing patient information
     * @return The patient information saved in the repository.
     */
    public PatientResponseDto postPatient(PatientRequestDto request) {
        return patientEntityToResponse(patientRepository.save(patientRequestToEntity(request)));
    }

    /**
     * @param id The ID of the patient
     * @return The patient information retrieved from the repository.
     * @throws RuntimeException if the patient with the given ID does not exist.
     */
    public PatientResponseDto getPatient(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(RuntimeException::new);
        return patientEntityToResponse(patient);
    }

    /**
     * This method updates an existing patient in the patient repository by its ID.
     * @param id The ID of the patient to update.
     * @param request The patient request containing the updated patient information.
     * @return The patient response DTO populated with the updated patient information saved.
     * @throws RuntimeException if the patient with the given ID does not exist.
     */
    public PatientResponseDto putPatient(int id, PatientRequestDto request) {
        Patient patient = patientRepository.findById(id).orElseThrow(RuntimeException::new);
        patientRequestToEntity(request, patient);
        return patientEntityToResponse(patientRepository.save(patient));
    }

    /**
     * This method deletes a patient by setting its record status to "D" (Deleted).
     * @param id The ID of the patient to delete.
     * @return The patient response DTO populated with the updated patient information saved in the repository.
     * @throws RuntimeException if the patient with the given ID does not exist in the repository.
     */
    public PatientResponseDto delete(int id) {
        Patient patient = patientRepository.findById(id).orElseThrow(RuntimeException::new);
        patient.setRecordStatus(RecordStatus.D);
        return patientEntityToResponse(patientRepository.save(patient));
    }

    /**
     This method registers a patient by converting the request DTO to an entity, saving the entity to the patient
     repository and sending a registration email
     @param request The registration request DTO containing patient information.
     @return The registration response DTO containing patient information.
     */
    public RegistrationResponseDto register(RegistrationRequestDto request) {
        Patient patient = patientRequestToEntityRegistration(request);
        patientRepository.save(patient);
        emailSender.sendRegistrationEmail(patient);
        return patientEntityToResponseRegistration();
    }

    /**
     This method activates a patient's account by checking the activation code against the code stored
     in the patient entity. If the codes match, the patient's record status is set to "A".
     A response DTO is returned containing the patient's first name and a status of "OK".
     If the activation code is invalid, an exception is thrown.
     @param request The activate request DTO containing the patient's email and activation code.
     @return The activate response DTO containing the patient's first name and a status of "OK".
     @throws InvalidActivationCodeException if the activation code is invalid.
     @throws UserNotFoundException if a patient with the specified email address cannot be found.
     */
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

    /**
     * This method helps convert a patient request DTO into a patient entity.
     * @param request The patient request DTO containing patient information.
     * @return The patient entity populated with the patient information from the request DTO.
     */
    private Patient patientRequestToEntity(PatientRequestDto request) {
        Patient patient = new Patient();
        return patientRequestToEntity(request, patient);
    }

    /**
     * This  method populates a patient entity with information from a patient request DTO.
     * @param request The patient request DTO containing patient information.
     * @param patient The patient entity to be populated.
     * @return The patient entity populated with the patient information from the request DTO
     */
    private Patient patientRequestToEntity(PatientRequestDto request, Patient patient){
        patient.setFirstname(request.getFirstname());
        patient.setLastname(request.getLastname());
        patient.setTelephoneNumber(request.getTelephoneNumber());
        patient.setEmail(request.getEmail());
        return patient;
    }

    /**
     * This  method converts a patient entity to a patient response DTO.
     * @param patient The patient entity containing patient information.
     * @return The patient response DTO populated with the patient information from the entity.
     */
    public PatientResponseDto patientEntityToResponse(Patient patient){
        PatientResponseDto response = new PatientResponseDto();
        response.setFirstname(patient.getFirstname());
        response.setLastname(patient.getLastname());
        response.setTelephoneNumber(patient.getTelephoneNumber());
        response.setEmail(patient.getEmail());
        return response;
    }

    /**
     * This private helper method converts a registration request DTO to a patient entity.
     * @param request The registration request DTO containing patient information.
     * @return The patient entity populated with the patient information from the registration request DTO.
     */
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

    /**
     This private helper method converts a patient entity to a registration response DTO.
     @return The registration response DTO indicating that the registration was successful.
     */
    private RegistrationResponseDto patientEntityToResponseRegistration(){
        RegistrationResponseDto response = new RegistrationResponseDto();
        response.setStatus(BaseResponse.Status.OK);
        return response;
    }

}

