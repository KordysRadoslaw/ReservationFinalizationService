package com.restaurationaws.reservationfinalizationservice.repository;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationQueryImpl implements ReservationQuery{
    private final ReservationRepository reservationRepository;

    public ReservationQueryImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> getByStatus(String status) {
        List<Reservation> reservations = reservationRepository.getAll();
        List<Reservation> reservationsByStatus = new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getStatus().equals(status)) {
                reservationsByStatus.add(reservation);
            }
        }

        return reservationsByStatus;
    }
}