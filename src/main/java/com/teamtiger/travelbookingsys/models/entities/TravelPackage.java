package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "travel_packages")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "package_name")
    private String packageName;
    @Column(name = "description")
    private String description;
    @Column(name = "destination")
    private String destination;
    @Column(name = "duration")
    private int duration;
    @Column(name = "price_per_person")
    private double pricePerPerson;
}