package com.restaurationaws.reservationfinalizationservice.repository;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;


import java.util.List;
import java.util.Optional;

public interface ReservationRepository {

    List<Reservation> getAllReservations();

    Reservation getReservationById(String reservationId);

    boolean updateReservationStatus(String reservationId, String status);

    void deleteReservation(String reservationId);
}
