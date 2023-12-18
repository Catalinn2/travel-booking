package com.teamtiger.travelbookingsys.services;

import com.teamtiger.travelbookingsys.models.dtos.UserDTO;
import com.teamtiger.travelbookingsys.models.entities.User;
import com.teamtiger.travelbookingsys.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::createUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::createUserResponseDTO);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();
            existingUser.setPhoneNumber(updatedUserDTO.getPhoneNumber());
            existingUser.setFirstName(updatedUserDTO.getFirstName());
            existingUser.setLastName(updatedUserDTO.getLastName());
            existingUser.setEmail(updatedUserDTO.getEmail());
            return createUserResponseDTO(existingUser);
        } else {
            throw new IllegalArgumentException("User with ID " + id + " not found");
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private static User createUserEntity(UserDTO userDTO) {
        User userEntity = new User();
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPhoneNumber(userDTO.getPhoneNumber());
        return userEntity;
    }

    private UserDTO createUserResponseDTO(User savedUser) {
        UserDTO userResponseDTO = new UserDTO();
        userResponseDTO.setId(savedUser.getId());
        userResponseDTO.setFirstName(savedUser.getFirstName());
        userResponseDTO.setLastName(savedUser.getLastName());
        userResponseDTO.setEmail(savedUser.getEmail());
        userResponseDTO.setPhoneNumber(savedUser.getPhoneNumber());

        return userResponseDTO;
    }
}