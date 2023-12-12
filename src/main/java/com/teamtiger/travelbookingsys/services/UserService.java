package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
}
