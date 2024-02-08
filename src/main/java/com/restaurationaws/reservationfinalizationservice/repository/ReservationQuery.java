package com.restaurationaws.reservationfinalizationservice.repository;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.List;

public interface ReservationQuery {

    List<Reservation> getByStatus(String status);
}
