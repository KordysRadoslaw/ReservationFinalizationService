package com.restaurationaws.reservationfinalizationservice.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.restaurationaws.reservationfinalizationservice.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.restaurationaws.reservationfinalizationservice"})
public class ReservationFinalizationServiceHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

    @Autowired
    private final ReservationService reservationService;

    public ReservationFinalizationServiceHandler(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Handling http post for ???? API endpoint");
        String reservationId = input.getBody();

        boolean result = reservationService.finalizeReservation(reservationId);

        if(!result){

            return new APIGatewayProxyResponseEvent().withStatusCode(400).withBody("Reservation not found or not exist for reservation id: " + reservationId);
        }

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Reservation finalized");


    }
}
