package com.teamtiger.travelbookingsys.exceptions.order;

public class OrderPeopleCountExceedsLimitException extends RuntimeException{
    public OrderPeopleCountExceedsLimitException(String message) {
        super(message);
    }
}
