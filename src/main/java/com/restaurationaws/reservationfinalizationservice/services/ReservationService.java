package com.restaurationaws.reservationfinalizationservice.services;


import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;




public class ReservationService{



    private final Table reservationTable;


    public ReservationService( DynamoDB dynamoDB) {

        this.reservationTable = dynamoDB.getTable("RestaurantReservation");
    }

}
