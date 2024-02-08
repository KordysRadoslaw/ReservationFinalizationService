package com.restaurationaws.reservationfinalizationservice.repository;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationRepositoryImpl implements ReservationRepository{
    private final Table reservationTable;

    public ReservationRepositoryImpl(DynamoDB dynamoDB) {
        this.reservationTable = dynamoDB.getTable("RestaurantReservation");
    }
    @Override
    public Reservation getById(String reservationId) {
        Item item = reservationTable.getItem("reservationId", reservationId);
        if (item != null) {
            return createReservationFromItem(item);
        }
        return null;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        for (Item item : reservationTable.scan()) {
            reservations.add(createReservationFromItem(item));
        }
        return reservations;
    }

    @Override
    public void add(Reservation reservation) {

    }

    @Override
    public void update(Reservation reservation) {

    }

    @Override
    public void delete(String reservationId) {

    }

    @Override
    public boolean updateReservationStatus(String reservationId, String status) {
        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                    .withPrimaryKey("reservationId", reservationId)
                    .withUpdateExpression("set reservationStatus = :s")
                    .withValueMap(new ValueMap().withString(":s", status));

            UpdateItemOutcome outcome = reservationTable.updateItem(updateItemSpec);
            return outcome.getUpdateItemResult().getSdkHttpMetadata().getHttpStatusCode() == 200;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update reservation status for reservationId: " + reservationId, e);
        }
    }

    private Reservation createReservationFromItem(Item item) {
        Reservation reservation = new Reservation();
        reservation.setReservationId(item.getString("reservationId"));
        reservation.setDate(item.getString("date"));
        reservation.setEmail(item.getString("email"));
        reservation.setFirstName(item.getString("firstName"));
        reservation.setLastName(item.getString("lastName"));
        reservation.setNummberOfGuests(item.getString("nummberOfGuests"));
        reservation.setStatus(item.getString("status"));
        return reservation;
    }
}
