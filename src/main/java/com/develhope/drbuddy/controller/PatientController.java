package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.dto.*;
import com.develhope.drbuddy.service.PatientService;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.PublicEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    private PatientService patientService;


    @PostMapping("/register")
    @PublicEndpoint
    public RegistrationResponseDto register(@RequestBody RegistrationRequestDto request) {
        return patientService.register(request);
    }


    @PostMapping("/activate")
    @PublicEndpoint
    public ActivateResponseDto activate(@RequestBody ActivateRequestDto request) {
        return patientService.activate(request);
    }

}