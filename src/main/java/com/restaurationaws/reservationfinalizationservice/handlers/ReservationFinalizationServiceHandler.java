package com.restaurationaws.reservationfinalizationservice.handlers;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.repository.ReservationDynamoDB;
import com.restaurationaws.reservationfinalizationservice.services.ReservationService;

import java.util.List;

public class ReservationFinalizationServiceHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final DynamoDB dynamoDB;
    private final ReservationDynamoDB reservationRepository;
    private final ReservationService reservationService;

    public ReservationFinalizationServiceHandler(DynamoDB dynamoDB, ReservationService reservationService, ReservationDynamoDB reservationRepository) {
        this.dynamoDB = dynamoDB;
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }
    public ReservationFinalizationServiceHandler() {
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClient.builder().build());
        this.reservationRepository = new ReservationDynamoDB(dynamoDB);
        this.reservationService = new ReservationService(dynamoDB);
    }



    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http post for /restaurant/finishReservation API endpoint");

        if ("/restaurant/cancelReservation".equals(input.getPath())) {
            return cancelReservation(input, context);

        } else if ("/restaurant/deleteReservation".equals(input.getPath())) {
            return deleteReservation(input, context);
        }
        Reservation userReservation;
        String reservationId = input.getQueryStringParameters().get("reservationId");

        try{
            userReservation = reservationRepository.getReservationById(reservationId);
        } catch (Exception e){
            logger.log(e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("reservation not found");
        }

        if (userReservation != null) {

            reservationRepository.updateReservationStatus(reservationId, "CONFIRMED");
            return new APIGatewayProxyResponseEvent().withStatusCode(200);
        } else {
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Reservation not found");
        }
    }

    public APIGatewayProxyResponseEvent cancelReservation(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http delete for /restaurant/cancelReservation API endpoint");

        if ("POST".equals(input.getHttpMethod())) {
            String reservationId = input.getQueryStringParameters().get("reservationId");
            reservationRepository.updateReservationStatus(reservationId, "CANCELLED");
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Reservation canceled");
        } else {
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Invalid HTTP method");
        }
    }



    public APIGatewayProxyResponseEvent deleteReservation(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http delete for /restaurant/deleteReservation API endpoint");
        if ("POST".equals(input.getHttpMethod())) {
            String reservationId = input.getQueryStringParameters().get("reservationId");
            reservationRepository.deleteReservation(reservationId);
            return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Reservation deleted");
        } else {
            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Invalid HTTP method");
        }
    }
}

