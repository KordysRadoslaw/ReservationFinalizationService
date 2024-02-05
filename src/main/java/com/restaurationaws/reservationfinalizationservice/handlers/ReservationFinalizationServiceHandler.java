package com.restaurationaws.reservationfinalizationservice.handlers;


import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.repository.ReservationRepository;
import com.restaurationaws.reservationfinalizationservice.repository.ReservationRepositoryImpl;
import com.restaurationaws.reservationfinalizationservice.services.ReservationService;

public class ReservationFinalizationServiceHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final DynamoDB dynamoDB;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public ReservationFinalizationServiceHandler(DynamoDB dynamoDB, ReservationService reservationService, ReservationRepository reservationRepository) {
        this.dynamoDB = dynamoDB;
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }
    public ReservationFinalizationServiceHandler() {
        this.dynamoDB = new DynamoDB(AmazonDynamoDBClient.builder().build());
        this.reservationRepository = new ReservationRepositoryImpl();
        this.reservationService = new ReservationService(reservationRepository, dynamoDB);
    }



    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http post for /restaurant/finishReservation API endpoint");

        try {
            if ("/restaurant/cancelReservation".equals(input.getPath())) {
                return cancelReservation(input, context);

            } else if ("/restaurant/deleteReservation".equals(input.getPath())) {
                return deleteReservation(input, context);
            }


            logger.log("jestem w srodku");

            logger.log(" a to user ---"+ input.getQueryStringParameters().get("reservationId"));
            String reservationId = input.getQueryStringParameters().get("reservationId");

            Reservation userReservation = reservationService.getReservationById(reservationId);
            logger.log("ussss" + userReservation);
            if (userReservation != null) {

                reservationService.updateReservationStatus(reservationId, "CONFIRMED");
                return new APIGatewayProxyResponseEvent().withStatusCode(200);
            } else {
                return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Reservation not found");
            }


        } catch (Exception e) {
            logger.log(e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("wrong path");
        }
    }

    public APIGatewayProxyResponseEvent cancelReservation(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http delete for /restaurant/cancelReservation API endpoint");

        try {
            if ("POST".equals(input.getHttpMethod())) {
                String reservationId = input.getQueryStringParameters().get("reservationId");
                reservationService.updateReservationStatus(reservationId, "CANCELLED");
                return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Reservation canceled");
            } else {
                return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Invalid HTTP method");
            }
        }catch (Exception e){
            logger.log(e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("wrong path");
        }
    }

    public APIGatewayProxyResponseEvent deleteReservation(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http delete for /restaurant/deleteReservation API endpoint");

        try {
            if ("POST".equals(input.getHttpMethod())) {
                String reservationId = input.getQueryStringParameters().get("reservationId");
                reservationService.deleteReservation(reservationId);
                return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Reservation deleted");
            } else {
                return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Invalid HTTP method");
            }
        }catch (Exception e){
            logger.log(e.getMessage());
            return new APIGatewayProxyResponseEvent().withStatusCode(500).withBody("wrong path");
        }
    }

}
