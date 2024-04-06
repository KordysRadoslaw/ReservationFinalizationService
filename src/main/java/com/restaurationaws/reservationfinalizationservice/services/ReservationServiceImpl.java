package com.restaurationaws.reservationfinalizationservice.services;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.repositories.ReservationRepository;

import java.util.List;

/**
 * Implementation of Reservation Service
 */

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

    /**
     * Get reservation by id
     * @param reservationId
     * @return Reservation object present in the database
     */
    @Override
    public Reservation getReservationById(String reservationId) {
        return reservationRepository.getById(reservationId);
    }

    /**
     * Get all reservations
     * @return List of all reservations represented as Reservation objects
     */

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.getAll();
    }

    /**
     * Update reservation status
     * @param reservationId
     * @param status
     * @return true if the status was updated successfully, false otherwise
     */
    @Override
    public boolean updateReservationStatus(String reservationId, String status) {
        return reservationRepository.updateReservationStatus(reservationId, status);
    }
}
