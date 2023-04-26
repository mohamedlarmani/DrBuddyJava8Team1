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

    /**

     This method creates a new doctor and saves it to the doctor repository.
     @param request The doctor request DTO containing doctor information
     @return The doctor information saved in the repository.
     */
    public DoctorResponseDto postDoctor(DoctorRequestDto request) {
        return doctorEntityToResponse(doctorRepository.save(doctorRequestToEntity(request)));
    }
    /**
     * @param id The ID of the doctor
     * @return The doctor information retrieved from the repository.
     * @throws RuntimeException if the doctor with the given ID does not exist.
     */

    public DoctorResponseDto getDoctor(int id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);
        return doctorEntityToResponse(doctor);
    }
    /**
     * This method updates an existing doctor in the doctor repository by its ID.
     * @param id The ID of the doctor to update.
     * @param request The doctor request containing the updated doctor information.
     * @return The doctor response DTO populated with the updated doctor information saved.
     * @throws RuntimeException if the patient with the given ID does not exist.
     */

    public DoctorResponseDto putDoctor(int id, DoctorRequestDto request) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);
        doctorRequestToEntity(request, doctor);
        return doctorEntityToResponse(doctorRepository.save(doctor));

    }

    /**
     * This method deletes a doctor by setting its record status to "D" (Deleted).
     * @param id The ID of the doctor to delete.
     * @return The doctor response DTO populated with the updated doctor information saved in the repository.
     * @throws RuntimeException if the doctor with the given ID does not exist in the repository.
     */
    public DoctorResponseDto delete(int id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(RuntimeException::new);
        doctor.setRecordStatus(RecordStatus.D);
        return doctorEntityToResponse(doctorRepository.save(doctor));
    }

    /**
     * This method gets a doctor page.
     * @param page The number of page who is being called.
     * @param pageSize The size of a page.
     * @return The doctor response DTO populated with updated doctor information saved in the repository.
     */
    public List<DoctorResponseDto> getDoctorPage(Integer page, Integer pageSize) {
        Page<Doctor> doctor = doctorRepository.findAll(PageRequest.of(page, pageSize));
        return doctorEntitiesToResponses(doctor.getContent());
    }

    /**
     * This method finds doctors from a pageable list
     * @param doctors List of present doctors.
     * @return a pageable list of doctors.
     */

    private List<DoctorResponseDto> doctorEntitiesToResponses(List<Doctor> doctors) {
        List<DoctorResponseDto> response = new ArrayList<>();
        for(Doctor doctor : doctors) {
            response.add(doctorEntityToResponse(doctor));
        }
        return response;
    }

    /**
     * This method helps convert a doctor request DTO into a doctor entity.
     * @param request The doctor request DTO containing doctor information.
     * @return The doctor entity populated with the doctor information from the request DTO.
     */
    private Doctor doctorRequestToEntity(DoctorRequestDto request) {
        Doctor doctor = new Doctor();
        return doctorRequestToEntity(request, doctor);
    }
    /**
     * This  method populates a doctor entity with information from a doctor request DTO.
     * @param request The doctor request DTO containing doctor information.
     * @param doctor The doctor entity to be populated.
     * @return The doctor entity populated with the doctor information from the request DTO
     */
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
    /**
     * This  method converts a doctor entity to a doctor response DTO.
     * @param doctor The doctor entity containing patient information.
     * @return The doctor response DTO populated with the doctor information from the entity.
     */

    public DoctorResponseDto doctorEntityToResponse(Doctor doctor){
        DoctorResponseDto response = new DoctorResponseDto();
        response.setEmail(doctor.getEmail());
        response.setFirstname(doctor.getFirstname());
        response.setLastname(doctor.getLastname());
        response.setTelephoneNumber(doctor.getTelephoneNumber());
        return response;
    }
}
