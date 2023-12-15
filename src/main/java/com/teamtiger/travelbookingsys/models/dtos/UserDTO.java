package com.teamtiger.travelbookingsys.models.dtos;

import lombok.Data;
import org.springframework.jdbc.core.SqlReturnType;
@Data
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
