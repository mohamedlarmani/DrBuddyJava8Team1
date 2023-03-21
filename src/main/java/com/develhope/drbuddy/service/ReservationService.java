package com.develhope.drbuddy.service;

import com.develhope.drbuddy.entities.Doctor;
import com.develhope.drbuddy.entities.Patient;
import com.develhope.drbuddy.entities.Reservation;
import com.develhope.drbuddy.entities.dto.ReservationRequestDto;
import com.develhope.drbuddy.entities.dto.ReservationResponseDto;
import com.develhope.drbuddy.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.ArrayList;
import java.util.List;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservationsByDoctor(Doctor doctor_id) {
        return reservationRepository.findBydoctor(doctor_id);
    }

    public List<Reservation> getReservationsByPatient(Patient patient_id) {
        return reservationRepository.findBypatient(patient_id);
    }


    public ReservationResponseDto postImmobile(ReservationRequestDto request) {
        return reservationEntityToResponse(reservationRepository.save(reservationRequestToEntity(request)));
    }

    public ReservationResponseDto getReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        return reservationEntityToResponse(reservation);
    }

    public ReservationResponseDto putReservation(int id, ReservationRequestDto request) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        reservationRequestToEntity(request, reservation);
        return reservationEntityToResponse(reservationRepository.save(reservation));

    }
    public ReservationResponseDto delete(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        reservationRepository.delete(reservation);
        return reservationEntityToResponse(reservation);
    }

    public List<ReservationResponseDto> getReservationPage(Integer page, Integer pageSize) {
        Page<Reservation> immobili = reservationRepository.findAll(PageRequest.of(page, pageSize));
        return reservationEntitiesToResponses(immobili.getContent());
    }

    private List<ReservationResponseDto> reservationEntitiesToResponses(List<Reservation> reservations) {
        List<ReservationResponseDto> response = new ArrayList<>();
        for(Reservation reservation : reservations) {
            response.add(reservationEntityToResponse(reservation));
        }
        return response;
    }

    private Reservation reservationRequestToEntity(ReservationRequestDto request) {
        Reservation reservation = new Reservation();
        return reservationRequestToEntity(request, reservation);
    }
    private Reservation reservationRequestToEntity(ReservationRequestDto request, Reservation reservation){
        reservation.setDateReservation(request.getDateReservation());
        reservation.setDoctor(request.getDoctor());
        reservation.setPatient(request.getPatient());
        return reservation;
    }

    private ReservationResponseDto reservationEntityToResponse(Reservation reservation){
        ReservationResponseDto response = new ReservationResponseDto();
        response.setDateReservation(reservation.getDateReservation());
        response.setDoctor(reservation.getDoctor());
        response.setPatient(reservation.getPatient());
        return response;
    }
}
