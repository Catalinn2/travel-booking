package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookings")
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    @Future
    private LocalDate date;
    @Column(name = "available_slots")
    @Min(value = 1, message = "Available slots should not be less than 1")
    @Positive(message = "Available slots should be a positive number")
    private int availableSpots;
    @ManyToOne
    @JoinColumn(name = "travel_packages_id")
    private TravelPackage travelPackage;
}