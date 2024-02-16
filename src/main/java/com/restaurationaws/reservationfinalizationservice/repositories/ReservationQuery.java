package com.restaurationaws.reservationfinalizationservice.repositories;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.List;

public interface ReservationQuery {

    List<Reservation> getByStatus(String status);
}
