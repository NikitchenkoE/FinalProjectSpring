package com.example.finalprojectspring.service;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IGeneraPageService;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneraPageService implements IGeneraPageService {

    private final UserRepository userRepository;

    @Autowired
    public GeneraPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Page<UserEntity> showAllMasters(Pageable pageable) {
        return userRepository.findAllByRoles(Role_Of_Users.ROLE_MASTER, pageable);
    }


    public Page<UserEntity> findPaginated(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo-1,pageSize);
        return this.userRepository.findAllByRoles(Role_Of_Users.ROLE_MASTER,pageable);
    }




}
