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
        User userEntity = createUserEntity(userDTO);

        User savedUser = userRepository.save(userEntity);
        log.info("User {} was saved", savedUser.getFirstName());

        return createUserResponseDTO(savedUser);
    }

    private static User createUserEntity(UserDTO userDTO) {
        User userEntity = new User();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        return userEntity;
    }

    private static UserDTO createUserResponseDTO(User savedUser) {
        UserDTO userResponseDTO = new UserDTO();
        userResponseDTO.setId(savedUser.getId());
        userResponseDTO.setFirstName(savedUser.getFirstName());
        userResponseDTO.setLastName(savedUser.getLastName());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setPhoneNumber(savedUser.getPhoneNumber());

        return userResponseDTO;
    }
}
