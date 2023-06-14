package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.dto.*;
import com.develhope.drbuddy.service.PatientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.PublicEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;


import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "*", allowedHeaders = {"Content-Type", "Authorization"})
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User", description = "The User API. Contains all the operations that can be performed on a user.")
@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    private PatientService patientService;



    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PublicEndpoint
    public RegistrationResponseDto register(@RequestBody RegistrationRequestDto request) {
        return patientService.register(request);
    }

    @PostMapping("/activate")
    @PublicEndpoint
    public ActivateResponseDto activate (@RequestBody ActivateRequestDto request) {
        return patientService.activate(request);
    }


    @DeleteMapping("/delete/{id}")
    @PublicEndpoint
    public PatientResponseDto delete (@PathVariable int id){
        return patientService.delete(id);
    }

}