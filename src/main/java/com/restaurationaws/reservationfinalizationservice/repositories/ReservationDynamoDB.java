package com.restaurationaws.reservationfinalizationservice.repositories;

import com.amazonaws.services.dynamodbv2.document.*;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.services.ReservationService;


import java.util.List;

/**
 * This class is responsible for the communication with the DynamoDB table
 */
public class ReservationDynamoDB {

    private final Table reservationTable;
    private final DynamoDB dynamoDB;
    private final ReservationService reservationService;
    private final ReservationQuery reservationQuery;

    public ReservationDynamoDB(DynamoDB dynamoDB, ReservationService reservationService, ReservationQuery reservationQuery) {
        this.dynamoDB = dynamoDB;
        this.reservationTable = dynamoDB.getTable("RestaurantReservation");
        this.reservationService = reservationService;
        this.reservationQuery = reservationQuery;
    }


    /**
     * This method updates the status of the reservation
     * @param reservationId
     * @param status
     * @return true if the status was updated, false otherwise
     */
    public boolean updateReservationStatus(String reservationId, String status) {
        try{
            return reservationService.updateReservationStatus(reservationId, status);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    /**
     * This method creates a new reservation
     * @param reservationId
     * @return the reservationId of the new reservation
     */
    public void deleteReservation(String reservationId) {
        try{
            reservationService.deleteReservation(reservationId);
        } catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    /**
     * This method creates a new reservation
     * @param reservationId
     * @return the reservationId of the new reservation
     */
    public Reservation getReservationById(String reservationId) {

        try{
            return reservationService.getReservationById(reservationId);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }




    public List<Reservation> getAllReservations() {
       try{
                return reservationService.getAllReservations();
            } catch (Exception e){
                throw new RuntimeException(e);
            }
        }
}

