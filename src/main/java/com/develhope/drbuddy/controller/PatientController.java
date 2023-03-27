package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.dto.*;
import com.develhope.drbuddy.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.PublicEndpoint;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.ZeroSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.web.bind.annotation.*;


@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
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
    @ZeroSecurity
    public ActivateResponseDto activate(@RequestBody ActivateRequestDto request) {
        return patientService.activate(request);
    }

}