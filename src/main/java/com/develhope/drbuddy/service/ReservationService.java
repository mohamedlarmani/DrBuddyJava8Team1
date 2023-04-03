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

    /**
     Retrieves a list of reservations for the specified doctor.
     @param doctor_id The id of the doctor for whom to retrieve reservations.
     @return A list of reservations for the specified doctor.
     */
    public List<Reservation> getReservationsByDoctor(Doctor doctor_id) {
        return reservationRepository.findBydoctor(doctor_id);
    }

    /**
     Retrieves a list of reservations for the specified patient.
     @param patient_id The id of the patient for whom to retrieve reservations.
     @return A list of reservations for the specified patient.
     */
    public List<Reservation> getReservationsByPatient(Patient patient_id) {
        return reservationRepository.findBypatient(patient_id);
    }


    /**
     Creates a new reservation based on the specified request.
     @param request The request object containing the details of the reservation to be created.
     @return A response object containing the details of the newly created reservation.
     */
    public ReservationResponseDto postReservation(ReservationRequestDto request) {
        return reservationEntityToResponse(reservationRepository.save(reservationRequestToEntity(request)));
    }

    /**
     Retrieves the reservation with the specified id.
     @param id The id of the reservation to retrieve.
     @return A response object containing the details of the retrieved reservation.
     @throws RuntimeException if no reservation with the specified id is found.
     */
    public ReservationResponseDto getReservation(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        return reservationEntityToResponse(reservation);
    }

    /**
     Updates the reservation with the specified id using the details in the specified request.
     @param id The id of the reservation to update.
     @param request The request object containing the details to use for the update.
     @return A response object containing the details of the updated reservation.
     @throws RuntimeException if no reservation with the specified id is found.
     */
    public ReservationResponseDto putReservation(int id, ReservationRequestDto request) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        reservationRequestToEntity(request, reservation);
        return reservationEntityToResponse(reservationRepository.save(reservation));

    }
    /**
     Deletes the reservation with the specified id.
     @param id The id of the reservation to delete.
     @return A response object containing the details of the deleted reservation.
     @throws RuntimeException if no reservation with the specified id is found.
     */
    public ReservationResponseDto delete(int id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(RuntimeException::new);
        reservationRepository.delete(reservation);
        return reservationEntityToResponse(reservation);
    }

    /**
     Retrieves a page of reservations.
     @return A list of response objects containing the details of the reservations on the specified page.
     */
    public List<ReservationResponseDto> getReservationPage(Integer page, Integer pageSize) {
        Page<Reservation> reservation = reservationRepository.findAll(PageRequest.of(page, pageSize));
        return reservationEntitiesToResponses(reservation.getContent());
    }

    /**
     Converts a list of Reservation entities to a list of ReservationResponseDto objects.
     */
    private List<ReservationResponseDto> reservationEntitiesToResponses(List<Reservation> reservations) {
        List<ReservationResponseDto> response = new ArrayList<>();
        for(Reservation reservation : reservations) {
            response.add(reservationEntityToResponse(reservation));
        }
        return response;
    }

    /**
     Converts a ReservationRequestDto object to a Reservation entity.
     */
    private Reservation reservationRequestToEntity(ReservationRequestDto request) {
        Reservation reservation = new Reservation();
        return reservationRequestToEntity(request, reservation);
    }
    /**
     Updates the fields of the specified Reservation entity using the fields from the specified request.
     */
    private Reservation reservationRequestToEntity(ReservationRequestDto request, Reservation reservation){
        reservation.setDateReservation(request.getDateReservation());
        reservation.setDoctor(request.getDoctor());
        reservation.setPatient(request.getPatient());
        return reservation;
    }

    /**
     Converts a Reservation entity to a ReservationResponseDto object by populating the fields of the DTO with the values from the entity.
     */
    private ReservationResponseDto reservationEntityToResponse(Reservation reservation){
        ReservationResponseDto response = new ReservationResponseDto();
        response.setDateReservation(reservation.getDateReservation());
        response.setDoctor(reservation.getDoctor());
        response.setPatient(reservation.getPatient());
        return response;
    }
}
