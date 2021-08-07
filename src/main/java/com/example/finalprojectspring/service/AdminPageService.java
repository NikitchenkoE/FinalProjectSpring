package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.exeption.ApiRequestExeption;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdminPageService implements IAdminPageService {

    private final UserRepository userRepository;

    @Autowired
    public AdminPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public List<UserEntity> findAllwithRoleUser() {

        return userRepository.findAllByRoles(Role_Of_Users.ROLE_USER);
    }

    @Transactional
    public void deleteUserByEmail(String email){

        final Optional<UserEntity> existenceUserByEmail =
                Optional.ofNullable(userRepository.findByEmail(email));

        if (!existenceUserByEmail.isPresent()){
            throw new ApiRequestExeption("This user do not exist or already deleted");
        }

        userRepository.deleteByEmail(email);
    }
}
