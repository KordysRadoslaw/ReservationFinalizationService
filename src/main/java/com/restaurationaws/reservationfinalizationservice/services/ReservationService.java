package com.restaurationaws.reservationfinalizationservice.services;



import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.repository.ReservationRepository;
import java.util.List;



public class ReservationService implements ReservationRepository{

    private final ReservationRepository reservationRepository;

    private final Table reservationTable;

    public ReservationService(ReservationRepository reservationRepository, DynamoDB dynamoDB) {
        this.reservationRepository = reservationRepository;
        this.reservationTable = dynamoDB.getTable("RestaurantReservation");
    }

    public boolean updateReservationStatus(String reservationId, String status) {
        try {
            UpdateItemSpec updateItemSpec = new UpdateItemSpec()
                    .withPrimaryKey("reservationId", reservationId)
                    .withUpdateExpression("set #s = :s")
                    .withValueMap(new ValueMap().withString(":s", status));

            UpdateItemOutcome outcome = reservationTable.updateItem(updateItemSpec);
            //return outcome.getUpdateItemResult().getSdkHttpMetadata().getHttpStatusCode() == 200;
            return reservationRepository.updateReservationStatus(reservationId, status);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteReservation(String reservationId) {
        reservationRepository.deleteReservation(reservationId);
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
                reservation.setNummberOfGuests(item.getString("nummberOfGuests"));
                reservation.setStatus(item.getString("status"));

                return reservationRepository.getReservationById(reservationId);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        try {
            List<Reservation> reservation = reservationRepository.getAllReservations();

            return reservation;
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
