package com.teamtiger.travelbookingsys.models.dtos;

import lombok.Data;

@Data
public class TravelPackageDTO {

    private Long id;

    private String packageName;
    private String description;
    private String destination;
    private int duration;
    private double pricePerPerson;
}
