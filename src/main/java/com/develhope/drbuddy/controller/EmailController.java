package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.dto.EmailDto;
import com.develhope.drbuddy.service.EmailService;
import com.develhope.drbuddy.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/notification")
    public ResponseEntity sendNotification(@RequestBody EmailDto payload){
        try{
            Optional<Patient> patientToNotify = patientService.getPatientById(payload.getContactId());
            System.out.println("patientToNotify: " + patientToNotify);
            if (patientToNotify == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("I couldn't find the patient \uD83E\uDD2F");
            }
            emailService.sendTo(patientToNotify.get().getEmail(), payload.getTitle(), payload.getText());
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
