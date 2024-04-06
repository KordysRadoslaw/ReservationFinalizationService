package com.restaurationaws.reservationfinalizationservice.repositories;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the ReservationQuery interface and provides the implementation for the getByStatus method.
 */
public class ReservationQueryImpl implements ReservationQuery{
    private final ReservationRepository reservationRepository;

    public ReservationQueryImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * This method returns a list of reservations with the specified status.
     *
     * @param status The status of the reservations to retrieve.
     * @return A list of reservations with the specified status.
     */
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