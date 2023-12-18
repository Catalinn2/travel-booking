package com.teamtiger.travelbookingsys.exceptions;

public class TravelPackageNotFoundException extends RuntimeException {
    public TravelPackageNotFoundException(String message) {
        super(message);
    }
}