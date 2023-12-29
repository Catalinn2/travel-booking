package com.teamtiger.travelbookingsys.exceptions.booking;

public class BookingIdInvalidException extends RuntimeException{
    public BookingIdInvalidException(String message) {
        super(message);
    }
}
