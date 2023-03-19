package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.enums.RecordStatus;
import com.develhope.drbuddy.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorService() {
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(int doctorId) {
        doctorRepository.getById(doctorId).setRecordStatus(RecordStatus.D);
    }

    public Optional<Doctor> getDoctorById(int doctorId){
        Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
        if (optionalDoctor.isPresent() && optionalDoctor.get().getRecordStatus() == RecordStatus.A) {
            return optionalDoctor;
        } else {
            throw new EntityNotFoundException("Doctor with id " + doctorId + " not found ");
        }
    }
}
