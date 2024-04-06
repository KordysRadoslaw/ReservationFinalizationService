package com.restaurationaws.reservationfinalizationservice.repositories;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.List;

/**
 * This interface is used to query the reservations by status.
 */
public interface ReservationQuery {

/**
     * This method is used to get the reservations by status.
     *
     * @param status The status of the reservations.
     * @return The list of reservations with the given status.
     */
    List<Reservation> getByStatus(String status);
}
