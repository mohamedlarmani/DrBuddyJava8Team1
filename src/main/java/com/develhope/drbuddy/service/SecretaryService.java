package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Secretary;
import com.develhope.drbuddy.entities.Reservation;
import com.develhope.drbuddy.entities.dto.*;
import com.develhope.drbuddy.enums.RecordStatus;
import com.develhope.drbuddy.exception.InvalidActivationCodeException;
import com.develhope.drbuddy.exception.UserNotFoundException;
import com.develhope.drbuddy.repository.SecretaryRepository;
import com.develhope.drbuddy.utilities.EmailSender;
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

        public SecretaryResponseDto postSecretary(SecretaryRequestDto request) {
            return secretaryEntityToResponse(secretaryRepository.save(secretaryRequestToEntity(request)));
        }

        public SecretaryResponseDto getSecretary(int id) {
            Secretary secretary = secretaryRepository.findById(id).orElseThrow(RuntimeException::new);
            return secretaryEntityToResponse(secretary);
        }

        public SecretaryResponseDto putSecretary(int id, SecretaryRequestDto request) {
            Secretary secretary = secretaryRepository.findById(id).orElseThrow(RuntimeException::new);
            secretaryRequestToEntity(request, secretary);
            return secretaryEntityToResponse(secretaryRepository.save(secretary));
        }


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

        private Secretary secretaryRequestToEntity(SecretaryRequestDto request) {
            Secretary secretary = new Secretary();
            return secretaryRequestToEntity(request, secretary);
        }

        private Secretary secretaryRequestToEntity(SecretaryRequestDto request, Secretary secretary){
            secretary.setFirstname(request.getFirstname());
            secretary.setLastname(request.getLastname());
            secretary.setTelephoneNumber(request.getTelephoneNumber());
            secretary.setEmail(request.getEmail());
            return secretary;
        }
        private SecretaryResponseDto secretaryEntityToResponse(Secretary secretary){
            SecretaryResponseDto response = new SecretaryResponseDto();
            response.setFirstname(secretary.getFirstname());
            response.setLastname(secretary.getLastname());
            response.setTelephoneNumber(secretary.getTelephoneNumber());
            response.setEmail(secretary.getEmail());
            return response;
        }

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

        private RegistrationResponseDto secretaryEntityToResponseRegistration(){
            RegistrationResponseDto response = new RegistrationResponseDto();
            response.setStatus(BaseResponse.Status.OK);
            return response;
        }
}