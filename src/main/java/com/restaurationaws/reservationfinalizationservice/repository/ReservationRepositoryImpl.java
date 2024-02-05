package com.restaurationaws.reservationfinalizationservice.repository;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationRepositoryImpl implements ReservationRepository {

    private final Map<String, Reservation> reservations;

    public ReservationRepositoryImpl() {
        this.reservations = new HashMap<>();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations.values());
    }

    @Override
    public Reservation getReservationById(String reservationId) {
        return reservations.get(reservationId);
    }

    @Override
    public boolean updateReservationStatus(String reservationId, String status) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.setStatus(status);
            return true;
        }
        return false;
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservations.remove(reservationId);
    }
}