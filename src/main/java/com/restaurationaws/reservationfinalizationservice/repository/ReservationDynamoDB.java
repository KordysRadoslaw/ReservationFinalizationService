package com.restaurationaws.reservationfinalizationservice.repository;

import com.amazonaws.services.dynamodbv2.document.*;

import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;


import java.util.ArrayList;
import java.util.List;

public class ReservationDynamoDB {

    private final Table reservationTable;
    private final DynamoDB dynamoDB;

    public ReservationDynamoDB(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
        this.reservationTable = dynamoDB.getTable("RestaurantReservation");
    }

    public boolean updateReservationStatus(String reservationId, String status) {
        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                    .withPrimaryKey("reservationId", reservationId)
                    .withUpdateExpression("set #s = :s")
                    .withValueMap(new ValueMap().withString(":s", status));

            UpdateItemOutcome outcome = reservationTable.updateItem(updateItemSpec);
            return outcome.getUpdateItemResult().getSdkHttpMetadata().getHttpStatusCode() == 200;


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteReservation(String reservationId) {
        try{
            DeleteItemOutcome outcome = reservationTable.deleteItem(new PrimaryKey("reservationId", reservationId));
            if(outcome.getDeleteItemResult().getSdkHttpMetadata().getHttpStatusCode() != 200){
                throw new RuntimeException("Failed to delete reservation for reservationId: " + reservationId);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Reservation getReservationById(String reservationId) {
        try {
            Item item = reservationTable.getItem("reservationId", reservationId);
            if (item != null) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(item.getString("reservationId"));
                reservation.setDate(item.getString("date"));
                reservation.setEmail(item.getString("email"));
                reservation.setFirstName(item.getString("firstName"));
                reservation.setLastName(item.getString("lastName"));
                reservation.setNummberOfGuests(item.getString("numberOfGuests"));
                reservation.setStatus(item.getString("status"));
                return reservation;
            } else {

                throw new RuntimeException("Reservation not found for reservationId: " + reservationId);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }




    public List<Reservation> getAllReservations() {
        try {

            List<Reservation> reservations = new ArrayList<>()  ;
            for(Item item : reservationTable.scan()){
                Reservation reservation = new Reservation();
                reservation.setReservationId(item.getString("reservationId"));
                reservation.setDate(item.getString("date"));
                reservation.setEmail(item.getString("email"));
                reservation.setFirstName(item.getString("firstName"));
                reservation.setLastName(item.getString("lastName"));
                reservation.setNummberOfGuests(item.getString("nummberOfGuests"));
                reservation.setStatus(item.getString("status"));
                reservations.add(reservation);
            }
            System.out.println("ReservationService.getAllReservations: " + reservations);
            return reservations;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
