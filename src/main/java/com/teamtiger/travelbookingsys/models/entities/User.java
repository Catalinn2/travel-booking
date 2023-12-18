package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.boot.model.process.internal.UserTypeResolution;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;

}