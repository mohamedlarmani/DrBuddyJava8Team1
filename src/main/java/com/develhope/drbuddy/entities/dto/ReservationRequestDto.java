package com.develhope.drbuddy.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class ReservationRequestDto {

    @NotNull
    @Column(name = "date_reservation", nullable = false)
    private LocalDateTime dateReservation;

    public ReservationRequestDto (){};

    public ReservationRequestDto(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }

    public LocalDateTime getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDateTime dateReservation) {
        this.dateReservation = dateReservation;
    }
}
