package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.MasterOcupationEntity;
import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.exeption.ApiRequestExeption;
import com.example.finalprojectspring.interfaices.IAdminPageService;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminPageService implements IAdminPageService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AdminPageService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public List<UserEntity> findAllwithRoleUser() {
        return userRepository.findAllByRoles(Role_Of_Users.ROLE_USER);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        final Optional<UserEntity> existenceUserByEmail =
                Optional.ofNullable(Optional.ofNullable(userRepository.findByEmail(email)).
                        orElseThrow(() ->
                                new ApiRequestExeption("This user do not exist or already deleted")));
        userRepository.deleteByEmail(email);
    }

    @Transactional
    public UserEntity createNewMaster(UserEntityDTO userEntityDTO) {
        MasterOcupationEntity occupation = new MasterOcupationEntity();
        occupation.setOcupation(userEntityDTO.getOccupation());
        UserEntity userEntity = new UserEntity().builder()
                .email(userEntityDTO.getEmail())
                .password(passwordEncoder.encode(userEntityDTO.getPassword()))
                .firstName(userEntityDTO.getFirstName())
                .lastName(userEntityDTO.getLastName())
                .roles(userEntityDTO.getRoles())
                .occupation(occupation)
                .build();

        final Optional<UserEntity> existenceUserByEmail =
                Optional.ofNullable(userRepository.findByEmail(userEntityDTO.getEmail()));
        if (existenceUserByEmail.isPresent()) {
            throw new ApiRequestExeption("Master already exist in db");
        }
        userRepository.save(userEntity);
        return userEntity;
    }

    public boolean userPresentInDb(UserEntityDTO userEntityDTO) {
        final Optional<UserEntity> existenceUserByEmail =
                Optional.ofNullable(userRepository.findByEmail(userEntityDTO.getEmail()));
        return existenceUserByEmail.isPresent();
    }

    public List<UserEntity> findAllWithRoleMaster() {
        return userRepository.findAllByRoles(Role_Of_Users.ROLE_MASTER);
    }

}
