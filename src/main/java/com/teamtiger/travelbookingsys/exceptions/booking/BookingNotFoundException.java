package com.teamtiger.travelbookingsys.exceptions.booking;

public class BookingNotFoundException extends RuntimeException{
    public BookingNotFoundException(String message) {
        super(message);
    }
}
