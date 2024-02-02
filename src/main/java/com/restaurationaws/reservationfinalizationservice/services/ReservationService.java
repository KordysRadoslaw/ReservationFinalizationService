package com.restaurationaws.reservationfinalizationservice.services;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Optional<Reservation> getReservationById(String reservationId) {
        return reservationRepository.findByReservationId(reservationId);
    }

    public boolean finalizeReservation(String reservationId) {
        Optional<Reservation> optionalReservation = reservationRepository.findByReservationId(reservationId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            String status = reservation.getStatus();

            if ("PENDING".equals(status)) {
                // Dodaj logikę finalizacji rezerwacji
                // Na przykład, ustaw pole finalized na true
                reservation.setStatus("CLOSED");
                reservationRepository.save(reservation);
                return true;
            } else if ("CLOSED".equals(status) || "CANCELLED".equals(status)) {
                return false;
            }
        }

        return false;
    }


}
