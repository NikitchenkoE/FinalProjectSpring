package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IRegistrationInterf;
import com.example.finalprojectspring.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j
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
        log.info("Added new user");
        UserEntity userEntity = UserEntity.builder()
                .email(userEntityDTO.getEmail())
                .password(passwordEncoder.encode(userEntityDTO.getPassword()))
                .firstName(userEntityDTO.getFirstName())
                .lastName(userEntityDTO.getLastName())
                .roles(userEntityDTO.getRoles())
                .money(userEntityDTO.getMoney())
                .build();
        userRepository.save(userEntity);
        return userEntity;
    }

    public boolean userPresentInDb(UserEntityDTO userEntityDTO) {
        log.error("User already exist in db");
        final Optional<UserEntity> existenceUserByEmail =
                Optional.ofNullable(userRepository.findByEmail(userEntityDTO.getEmail()));
        return existenceUserByEmail.isPresent();
    }
}
