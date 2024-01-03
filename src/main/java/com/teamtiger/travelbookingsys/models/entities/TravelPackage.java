package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "travel_packages")
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "package_name")
    @NotEmpty(message = "Package name cannot be empty or null")
    private String packageName;
    @Column(name = "description")
    @NotEmpty(message = "Description cannot be empty or null")
    private String description;
    @Column(name = "destination")
    @NotEmpty(message = "Destination cannot be empty or null")
    private String destination;
    @Column(name = "duration")
    @Min(value = 1,message = "Duration should not be less than 1")
    @Positive(message = "Duration should be a positive number")
    private int duration;
    @Column(name = "price")
    @Positive(message = "Price should be a positive number")
    private double price;
    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdByUser;
}