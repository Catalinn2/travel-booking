package com.teamtiger.travelbookingsys.exceptions.customer;

public class CustomerIdInvalidException extends RuntimeException {
    public CustomerIdInvalidException(String message) {
        super(message);
    }
}