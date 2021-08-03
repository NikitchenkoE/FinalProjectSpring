package com.example.finalprojectspring.Service;

import com.example.finalprojectspring.DTO.UserEntityDTO;
import com.example.finalprojectspring.Entities.UserEntity;
import com.example.finalprojectspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService implements IRegistrationInterf {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity addUserToDataBase(UserEntityDTO userEntityDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userEntityDTO.getEmail());
        userEntity.setPassword(userEntityDTO.getPassword());
        userEntity.setFirstName(userEntityDTO.getFirstName());
        userEntity.setLastName(userEntityDTO.getLastName());
        userEntity.setRoles(userEntityDTO.getRoles());

        userRepository.save(userEntity);
        return userEntity;
    }
}
