package com.example.finalprojectspring.service;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IGeneraPageService;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneraPageService implements IGeneraPageService {

    private final UserRepository userRepository;

    @Autowired
    public GeneraPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> showAllMasters() {
        return userRepository.findAllByRoles(Role_Of_Users.ROLE_MASTER);
    }
}
