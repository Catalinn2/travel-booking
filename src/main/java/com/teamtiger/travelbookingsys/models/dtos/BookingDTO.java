package com.teamtiger.travelbookingsys.models.dtos;

import com.teamtiger.travelbookingsys.models.entities.TravelPackage;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingDTO {
    private Long id;
    private LocalDate date;
    private int availableSpots;
    private long travelPackageId;
}
