package com.teamtiger.travelbookingsys.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotEmpty(message = "First name cannot be empty or null")
    private String firstName;
    @Column(name = "last_name")
    @NotEmpty(message = "Last name cannot be empty or null")
    private String lastName;
    @Column(name = "email", unique = true)
    @Email(message = "Email should be valid")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
}