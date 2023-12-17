package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
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
    private LocalDate date;
    @Column(name = "aviable_slots")
    private int availableSpots;
    @ManyToOne
    @JoinColumn(name = "travel_packages_id")
    private TravelPackage travelPackage;

}
