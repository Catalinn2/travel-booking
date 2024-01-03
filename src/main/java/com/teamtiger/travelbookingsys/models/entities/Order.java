package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking bookingId;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerId;
    @Column(name = "people_count")
    @Min(value = 1, message = "People Count should not be less than 1")
    @Positive(message = "People Count should be a positive number")
    private int peopleCount;
}