package com.restaurationaws.reservationfinalizationservice.repositories;

import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@EnableScan
public interface ReservationRepository extends CrudRepository<Reservation, String> {
    Optional<Reservation> findByReservationId(String reservationId);
}
