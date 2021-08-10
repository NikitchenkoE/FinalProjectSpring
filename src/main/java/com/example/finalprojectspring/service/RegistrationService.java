package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.exeption.ApiRequestExeption;
import com.example.finalprojectspring.interfaices.IRegistrationInterf;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RegistrationService implements IRegistrationInterf {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity addUserToDataBase(UserEntityDTO userEntityDTO) {
        UserEntity userEntity = new UserEntity().builder()
                .email(userEntityDTO.getEmail())
                .password(passwordEncoder.encode(userEntityDTO.getPassword()))
                .firstName(userEntityDTO.getFirstName())
                .lastName(userEntityDTO.getLastName())
                .roles(userEntityDTO.getRoles())
                .build();

//        final Optional<UserEntity> existenceUserByEmail =
//                Optional.ofNullable(userRepository.findByEmail(userEntityDTO.getEmail()));
//        if (existenceUserByEmail.isPresent()) {
//            throw new ApiRequestExeption("User already exist in db");
//        }

        userRepository.save(userEntity);
        return userEntity;
    }

    public boolean userPresentInDb(UserEntityDTO userEntityDTO) {
        final Optional<UserEntity> existenceUserByEmail =
                Optional.ofNullable(userRepository.findByEmail(userEntityDTO.getEmail()));

        return existenceUserByEmail.isPresent();
    }
}
