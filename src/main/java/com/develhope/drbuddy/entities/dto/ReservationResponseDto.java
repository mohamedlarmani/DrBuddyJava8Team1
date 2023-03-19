package com.develhope.drbuddy.entities.dto;

import com.develhope.drbuddy.entities.Reservation;

import java.time.LocalDateTime;

public class ReservationResponseDto {

    private LocalDateTime dateReservation;

    public ReservationResponseDto(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public static ReservationResponseDto fromReservation(Reservation reservation) {
        return new ReservationResponseDto(
                reservation.getDateReservation());
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
}
