package com.restaurationaws.reservationfinalizationservice.repository;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    Reservation getById(String reservationId);

    List<Reservation> getAll();

    void add(Reservation reservation);

    void update(Reservation reservation);

    void delete(String reservationId);

    boolean updateReservationStatus(String reservationId, String status);
}
