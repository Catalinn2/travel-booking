package com.teamtiger.travelbookingsys.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DetailedTravelPackageDTO {
    private Long id;
    private String packageName;
    private String description;
    private String destination;
    private int duration;
    private double price;
    private List<BookingDTO> bookings;
}