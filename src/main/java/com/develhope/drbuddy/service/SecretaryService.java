package com.develhope.drbuddy.service;


import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.entities.dto.*;
import com.develhope.drbuddy.enums.RecordStatus;
import com.develhope.drbuddy.exception.InvalidActivationCodeException;
import com.develhope.drbuddy.exception.UserNotFoundException;
import com.develhope.drbuddy.repository.SecretaryRepository;
import com.develhope.drbuddy.notifications.EmailSender;
import com.develhope.drbuddy.utilities.StringUtility;
import it.pasqualecavallo.studentsmaterial.authorization_framework.utils.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecretaryService {
    @Autowired
    private SecretaryRepository secretaryRepository;

    @Autowired
    private EmailSender emailSender;

    @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * This method creates a new secretary and saves it to the secretary repository.
     * @param request The secretary request DTO containing patient information
     * @return The secretary information saved in the repository.
     */
    public SecretaryResponseDto postSecretary(SecretaryRequestDto request) {
            return secretaryEntityToResponse(secretaryRepository.save(secretaryRequestToEntity(request)));
        }

    /**
     * @param id The ID of the secretary
     * @return The secretary information retrieved from the repository.
     * @throws RuntimeException if the secretary with the given ID does not exist.
     */
    public SecretaryResponseDto getSecretary(int id) {
            Secretary secretary = secretaryRepository.findById(id).orElseThrow(RuntimeException::new);
            return secretaryEntityToResponse(secretary);
        }

    /**
     This method updates an existing secretary in the secretary repository by their ID.
     @param id The ID of the patient to update.
     @param request The secretary request containing the updated secretary information.
     @return The secretary response DTO populated with the updated secretary information saved.
     @throws RuntimeException if the secretary with the given ID does not exist.
     */
    public SecretaryResponseDto putSecretary(int id, SecretaryRequestDto request) {
            Secretary secretary = secretaryRepository.findById(id).orElseThrow(RuntimeException::new);
            secretaryRequestToEntity(request, secretary);
            return secretaryEntityToResponse(secretaryRepository.save(secretary));
        }

    /**
     * This method deletes a secretary by setting its record status to "D" (Deleted).
     * @param id The ID of the secretary to delete.
     * @return The secretary response DTO populated with the updated doctor information saved in the repository.
     * @throws RuntimeException if the doctor with the given ID does not exist in the repository.
     */
    public SecretaryResponseDto delete(int id) {
        Secretary secretary = secretaryRepository.findById(id).orElseThrow(RuntimeException::new);
        secretary.setRecordStatus(RecordStatus.D);
        return secretaryEntityToResponse(secretaryRepository.save(secretary));
    }

    /**
     This method activates a secretary's account by checking the activation code against the code stored
     in the secretary entity. If the codes match, the secretary's record status is set to "A".
     A response DTO is returned containing the secretary's first name and a status of "OK".
     If the activation code is invalid, an exception is thrown.
     @param request The activate request DTO containing the secretary's email and activation code.
     @return The activate response DTO containing the secretary's first name and a status of "OK".
     @throws InvalidActivationCodeException if the activation code is invalid.
     @throws UserNotFoundException if a secretary with the specified email address cannot be found.
     */
    public ActivateResponseDto activate(ActivateRequestDto request) {
            Optional<Secretary> oSecretary = secretaryRepository.findByEmail(request.getEmail());
            Secretary secretary = oSecretary.orElseThrow(UserNotFoundException::new);
            if(request.getActivationCode().equals(secretary.getActivationCode())) {
                secretary.setActivationCode("null");
                secretary.setRecordStatus(RecordStatus.A);
                secretaryRepository.save(secretary);
                ActivateResponseDto response = new ActivateResponseDto();
                response.setStatus(BaseResponse.Status.OK);
                response.setFirstName(secretary.getFirstname());
                return response;
            } else {
                throw new InvalidActivationCodeException();
            }
        }

    /**
     This method helps convert a secretary request DTO into a secretary entity.
     @param request The secretary request DTO containing secretary information.
     @return The secretary entity populated with the secretary information from the request DTO.
     */
    private Secretary secretaryRequestToEntity(SecretaryRequestDto request) {
            Secretary secretary = new Secretary();
            return secretaryRequestToEntity(request, secretary);
        }

    /**
     This method populates a secretary entity with information from a secretary request DTO.
     @param request The secretary request DTO containing secretary information.
     @param secretary The secretary entity to be populated.
     @return The secretary entity populated with the secretary information from the request DTO.
     */
    private Secretary secretaryRequestToEntity(SecretaryRequestDto request, Secretary secretary){
            secretary.setFirstname(request.getFirstname());
            secretary.setLastname(request.getLastname());
            secretary.setTelephoneNumber(request.getTelephoneNumber());
            secretary.setEmail(request.getEmail());
            return secretary;
        }

    /**
     This method converts a secretary entity to a secretary response DTO.
     @param secretary The secretary entity containing secretary information.
     @return The secretary response DTO populated with the secretary information from the entity.
     */
    private SecretaryResponseDto secretaryEntityToResponse(Secretary secretary){
            SecretaryResponseDto response = new SecretaryResponseDto();
            response.setFirstname(secretary.getFirstname());
            response.setLastname(secretary.getLastname());
            response.setTelephoneNumber(secretary.getTelephoneNumber());
            response.setEmail(secretary.getEmail());
            return response;
        }

    /**
     This private helper method converts a registration request DTO to a secretary entity.
     @param request The registration request DTO containing secretary information.
     @return The secretary entity populated with the secretary information from the registration request DTO.
     */
    private Secretary secretaryRequestToEntityRegistration(RegistrationRequestDto request){
            Secretary secretary = new Secretary();
            secretary.setEmail(request.getEmail());
            secretary.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            System.out.println(bCryptPasswordEncoder.encode(request.getPassword()));
            secretary.setFirstname(request.getFirstname());
            secretary.setLastname(request.getLastname());
            secretary.setTelephoneNumber(request.getTelephoneNumber());
            secretary.setRecordStatus(RecordStatus.I);
            secretary.setActivationCode(StringUtility.generateRandomString(6));
            return secretary;
        }

    /**
     This private helper method converts a secretary entity to a registration response DTO.
     @return The registration response DTO indicating that the registration was successful.
     */
    private RegistrationResponseDto secretaryEntityToResponseRegistration(){
            RegistrationResponseDto response = new RegistrationResponseDto();
            response.setStatus(BaseResponse.Status.OK);
            return response;
        }
}