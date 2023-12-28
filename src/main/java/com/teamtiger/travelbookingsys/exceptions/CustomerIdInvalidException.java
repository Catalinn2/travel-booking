package com.teamtiger.travelbookingsys.exceptions;

public class CustomerIdInvalidException extends RuntimeException {
    public CustomerIdInvalidException(String message) {
        super(message);
    }
}