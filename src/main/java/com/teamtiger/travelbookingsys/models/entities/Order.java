package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
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
    private int peopleCount;
}