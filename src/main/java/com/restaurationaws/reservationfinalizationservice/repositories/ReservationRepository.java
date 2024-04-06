package com.restaurationaws.reservationfinalizationservice.repositories;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.List;

/**
 * Reservation Repository Interface
 */
public interface ReservationRepository {

    /**
     * Get reservation by id
     * @param reservationId
     * @return Reservation object present in the database
     */

    Reservation getById(String reservationId);

    /**
     * Get all reservations
     * @return List of all reservations represented as Reservation objects
     */
    List<Reservation> getAll();

    /**
     * Add new reservation
     * @param reservation
     */
    void add(Reservation reservation);

    /**
     * Update reservation
     * @param reservation
     */
    void update(Reservation reservation);

    /**
     * Delete reservation
     * @param reservationId
     */
    void delete(String reservationId);

    /**
     * Update reservation status
     * @param reservationId
     * @param status
     * @return true if the status was updated successfully, false otherwise
     */
    boolean updateReservationStatus(String reservationId, String status);
}
