package com.restaurationaws.reservationfinalizationservice.services;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.repositories.ReservationRepository;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void confirmReservation(String reservationId) {

    }

    @Override
    public void cancelReservation(String reservationId) {

    }

    @Override
    public void deleteReservation(String reservationId) {

    }

    @Override
    public Reservation getReservationById(String reservationId) {
        return reservationRepository.getById(reservationId);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAll();
    }

    @Override
    public boolean updateReservationStatus(String reservationId, String status) {
        return reservationRepository.updateReservationStatus(reservationId, status);
    }
}
