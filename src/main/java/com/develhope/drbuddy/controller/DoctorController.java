package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.dto.DoctorRequestDto;
import com.develhope.drbuddy.entities.dto.DoctorResponseDto;
import com.develhope.drbuddy.entities.dto.PatientResponseDto;
import com.develhope.drbuddy.repository.DoctorRepository;
import com.develhope.drbuddy.service.DoctorService;
import it.pasqualecavallo.studentsmaterial.authorization_framework.security.PublicEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping("/postDoctor")
    public DoctorResponseDto postDoctor(@RequestBody DoctorRequestDto request){
        return doctorService.postDoctor(request);
    }

    @GetMapping("/getDoctor/{doctorById}")
    public DoctorResponseDto getDoctorById(@PathVariable int doctorId){
        return doctorService.getDoctor(doctorId);
    }

    @PutMapping("/putDoctor/{id}")
    public DoctorResponseDto putDoctor(@PathVariable int doctorId, @RequestBody DoctorRequestDto request){
        return doctorService.putDoctor(doctorId, request);
    }

    @DeleteMapping("/delete/{id}")
    public DoctorResponseDto delete (@PathVariable int doctorId){
        return doctorService.delete(doctorId);
    }

}
