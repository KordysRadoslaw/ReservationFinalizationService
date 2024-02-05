package com.restaurationaws.reservationfinalizationservice;


import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.restaurationaws.reservationfinalizationservice.models.Reservation;
import com.restaurationaws.reservationfinalizationservice.services.ReservationService;

public class ReservationFinalizationServiceHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final DynamoDB dynamoDB;
    private final ReservationService reservationService;

    public ReservationFinalizationServiceHandler(DynamoDB dynamoDB, ReservationService reservationService) {
        this.dynamoDB = dynamoDB;
        this.reservationService = reservationService;
    }


    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http post for /restaurant/finishReservation API endpoint");

        if("POST".equals(input.getHttpMethod())){
            String reservationId = input.getPathParameters().get("reservationId");
            Reservation userReservation = reservationService.getReservationById(reservationId);
            if(userReservation != null){


            }

        }else{
            throw new RuntimeException("Invalid HTTP method");
        }

        return null;
    }

    public APIGatewayProxyResponseEvent deleteReservation(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http delete for /restaurant/deleteReservation API endpoint");

        if("POST".equals(input.getHttpMethod())){
            String reservationId = input.getPathParameters().get("reservationId");
            reservationService.deleteReservation(reservationId);
        }else{
            throw new RuntimeException("Invalid HTTP method");
        }
        return null;
    }


}
