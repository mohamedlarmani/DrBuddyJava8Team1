package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.dto.DoctorRequestDto;
import com.develhope.drbuddy.entities.dto.DoctorResponseDto;
import com.develhope.drbuddy.enums.RecordStatus;
import com.develhope.drbuddy.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public DoctorResponseDto postDoctor(DoctorRequestDto request) {
        return doctorEntityToResponse(doctorRepository.save(doctorRequestToEntity(request)));
    }

    public DoctorResponseDto getDoctor(int id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);
        return doctorEntityToResponse(doctor);
    }

    public DoctorResponseDto putDoctor(int id, DoctorRequestDto request) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);
        doctorRequestToEntity(request, doctor);
        return doctorEntityToResponse(doctorRepository.save(doctor));

    }
    public DoctorResponseDto delete(int id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);
        doctorRepository.delete(doctor);
        return doctorEntityToResponse(doctor);
    }

    public List<DoctorResponseDto> getDoctorPage(Integer page, Integer pageSize) {
        Page<Doctor> immobili = doctorRepository.findAll(PageRequest.of(page, pageSize));
        return doctorEntitiesToResponses(immobili.getContent());
    }

    private List<DoctorResponseDto> doctorEntitiesToResponses(List<Doctor> doctors) {
        List<DoctorResponseDto> response = new ArrayList<>();
        for(Doctor doctor : doctors) {
            response.add(doctorEntityToResponse(doctor));
        }
        return response;
    }

    private Doctor doctorRequestToEntity(DoctorRequestDto request) {
        Doctor doctor = new Doctor();
        return doctorRequestToEntity(request, doctor);
    }
    private Doctor doctorRequestToEntity(DoctorRequestDto request, Doctor doctor){
        doctor.setAddress(request.getAddress());
        doctor.setCity(request.getCity());
        doctor.setEmail(request.getEmail());
        doctor.setFirstname(request.getFirstname());
        doctor.setLastname(request.getLastname());
        doctor.setTelephoneNumber(request.getTelephoneNumber());
        doctor.setSecretary(request.getSecretary());
        return doctor;
    }

    private DoctorResponseDto doctorEntityToResponse(Doctor doctor){
        DoctorResponseDto response = new DoctorResponseDto();
        response.setAddress(doctor.getAddress());
        response.setCity(doctor.getCity());
        response.setEmail(doctor.getEmail());
        response.setFirstname(doctor.getFirstname());
        response.setLastname(doctor.getLastname());
        response.setTelephoneNumber(doctor.getTelephoneNumber());
        return response;
    }
}
