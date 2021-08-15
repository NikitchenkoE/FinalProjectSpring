package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
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
    public Page<UserEntityDTO> findPaginated(int pageNo, int pageSize, String occupation) {
        if (occupation == null || ALL.equals(occupation)) {
            return findPaginatedDto(pageNo, pageSize);
        }
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<UserEntity> list = userRepository.findAllByOccupation(occupation, pageable);
        Page<UserEntityDTO> sorted = new PageImpl<>(list.stream()
                .map(o -> userEntityToUserEntityDto(o))
                .collect(Collectors.toList()), pageable, list.getTotalElements());
        return sorted;
    }

    public Page<UserEntityDTO> findPaginatedDto(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<UserEntity> list = userRepository.findAllByRoles(Role_Of_Users.ROLE_MASTER, pageable);
        Page<UserEntityDTO> sorted = new PageImpl<>(list.stream()
                .map(o -> userEntityToUserEntityDto(o))
                .collect(Collectors.toList()), pageable, list.getTotalElements());
        return sorted;
    }

    public Page<UserEntity> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepository.findAllByRoles(Role_Of_Users.ROLE_MASTER, pageable);
    }


    private UserEntityDTO userEntityToUserEntityDto(UserEntity userEntity) {
        UserEntityDTO userEntityDTO = new UserEntityDTO().builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .roles(userEntity.getRoles())
                .occupation(userEntity.getOccupation().getOcupation())
                .averageRating(userEntity.getRatings().stream()
                        .mapToDouble(rating -> rating.getRating())
                        .average()
                        .orElse(0.0))
                .build();
        return userEntityDTO;
    }
}
