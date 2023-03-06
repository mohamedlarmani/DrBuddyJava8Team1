package com.develhope.drbuddy.controller;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@RestController
@RequestMapping("/Doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }


    @GetMapping("/{doctorId}")
    public Optional<Doctor> getDoctorById(@PathVariable int doctorId){
        Optional<Doctor> optionalDoctor = doctorService.getDoctorById(doctorId);
        if (optionalDoctor.isPresent()) {
            return optionalDoctor;
        } else {
            throw new EntityNotFoundException("Patient with id " + doctorId + " not found");
        }
    }

}
