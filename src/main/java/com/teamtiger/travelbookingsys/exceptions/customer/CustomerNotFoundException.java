package com.teamtiger.travelbookingsys.exceptions.customer;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
