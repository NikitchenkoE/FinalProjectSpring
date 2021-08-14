package com.example.finalprojectspring.service;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IGeneraPageService;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GeneraPageService implements IGeneraPageService {

    private final UserRepository userRepository;

    private final String ALL = "all";

    @Autowired
    public GeneraPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserEntity> findPaginated(int pageNo, int pageSize, String occupation) {
        if (occupation == null || ALL.equals(occupation)) {
            return findPaginated(pageNo, pageSize);
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<UserEntity> sortedList = userRepository.findAllByOccupation(occupation, pageable);
        return sortedList;
    }

    public Page<UserEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepository.findAllByRoles(Role_Of_Users.ROLE_MASTER, pageable);
    }


}
