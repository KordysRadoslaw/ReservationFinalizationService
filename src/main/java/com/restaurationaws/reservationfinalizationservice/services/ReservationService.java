package com.restaurationaws.reservationfinalizationservice.services;


import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.List;

/**
 * Reservation Service Interface
 */

public interface ReservationService{

    /**
     * Confirm a reservation
     * @param reservationId
     */
    void  confirmReservation(String reservationId);

    /**
     * Cancel a reservation
     * @param reservationId
     */
    void  cancelReservation(String reservationId);
    /**
     * Delete a reservation
     * @param reservationId
     */
    void  deleteReservation(String reservationId);

    /**
     * Get reservation by id
     * @param reservationId
     * @return Reservation object present in the database
     */
    Reservation getReservationById(String reservationId);

    /**
     * Get all reservations
     * @return List of all reservations represented as Reservation objects
     */
    List<Reservation> getAllReservations();

    /**
     * Update reservation status
     * @param reservationId
     * @param status
     * @return true if the status was updated successfully, false otherwise
     */

    boolean updateReservationStatus(String reservationId, String status);
}
