package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.dto.DoctorResponseDto;
import com.develhope.drbuddy.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/getDoctor/{doctorById}")
    public DoctorResponseDto getDoctorById(@PathVariable int doctorId){
        return doctorService.getDoctor(doctorId);
    }

}
