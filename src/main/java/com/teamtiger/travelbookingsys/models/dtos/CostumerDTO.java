package com.teamtiger.travelbookingsys.models.dtos;

import lombok.Data;

@Data
public class CostumerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
}
