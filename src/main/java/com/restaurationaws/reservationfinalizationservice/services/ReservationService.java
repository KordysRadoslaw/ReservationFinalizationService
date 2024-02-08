package com.restaurationaws.reservationfinalizationservice.services;


import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.List;


public interface ReservationService{

    void  confirmReservation(String reservationId);
    void  cancelReservation(String reservationId);
    void  deleteReservation(String reservationId);
    Reservation getReservationById(String reservationId);
    List<Reservation> getAllReservations();

    boolean updateReservationStatus(String reservationId, String status);
}
