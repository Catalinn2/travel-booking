package com.teamtiger.travelbookingsys.models.dtos;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private Long bookingId;
    private Long customerId;
    private int peopleCount;
}