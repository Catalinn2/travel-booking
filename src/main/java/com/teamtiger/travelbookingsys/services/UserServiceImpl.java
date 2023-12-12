package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.UserDTO;
import com.teamtiger.travelbookingsys.models.entities.User;
import com.teamtiger.travelbookingsys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User userEntity = new User();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setContact(userDTO.getContact());

        User savedUser = userRepository.save(userEntity);
        log.info("User {} was saved", savedUser.getFirstName());

        UserDTO userResponseDTO = new UserDTO();
        userResponseDTO.setId(savedUser.getId());
        userResponseDTO.setFirstName(savedUser.getFirstName());
        userResponseDTO.setLastName(savedUser.getLastName());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setContact(savedUser.getContact());

        return userResponseDTO;
    }
}
