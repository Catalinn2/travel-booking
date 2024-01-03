package com.teamtiger.travelbookingsys.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamtiger.travelbookingsys.exceptions.booking.BookingIdInvalidException;
import com.teamtiger.travelbookingsys.exceptions.booking.BookingNotFoundException;
import com.teamtiger.travelbookingsys.exceptions.customer.CustomerIdInvalidException;
import com.teamtiger.travelbookingsys.exceptions.customer.CustomerNotFoundException;
import com.teamtiger.travelbookingsys.exceptions.order.OrderPeopleCountExceedsLimitException;
import com.teamtiger.travelbookingsys.exceptions.travelPackage.TravelPackageNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private final ObjectMapper objectMapper;

    public ExceptionHandlerAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(TravelPackageNotFoundException.class)
    public ResponseEntity<String> handleTravelPackageNotFoundException(TravelPackageNotFoundException travelPackageNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", travelPackageNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException bookingNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", bookingNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(BookingIdInvalidException.class)
    public ResponseEntity<String> handleBookingIdInvalidException(BookingIdInvalidException bookingIdInvalidException) {
        return new ResponseEntity<>(objectToString(Map.of("message", bookingIdInvalidException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFoundException customerNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", customerNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(CustomerIdInvalidException.class)
    public ResponseEntity<String> handleCustomerNotFoundException(CustomerIdInvalidException customerIdInvalidException) {
        return new ResponseEntity<>(objectToString(Map.of("message", customerIdInvalidException.getMessage())), BAD_REQUEST);
    }

    @ExceptionHandler(OrderPeopleCountExceedsLimitException.class)
    public ResponseEntity<String> handleOrderPeopleCountExceedsLimitException(OrderPeopleCountExceedsLimitException orderPeopleCountExceedsLimit) {
        return new ResponseEntity<>(objectToString(Map.of("message", orderPeopleCountExceedsLimit.getMessage())), BAD_REQUEST);
    }
    private String objectToString(Object response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("Error processing response to string.");
            return "Internal error";
        }
    }
}