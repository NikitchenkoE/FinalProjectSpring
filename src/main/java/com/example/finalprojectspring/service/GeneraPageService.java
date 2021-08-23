package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.RoleOfUsers;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.IGeneraPageService;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class GeneraPageService implements IGeneraPageService {

    private final UserRepository userRepository;

    @Autowired
    public GeneraPageService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserEntityDTO> findPaginated(int pageNo, int pageSize, String occupation, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();


        String ALL = "all";
        if (occupation == null || ALL.equals(occupation)) {
            return findPaginatedDto(pageNo, pageSize,sortField,sortDirection);
        }

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        Page<UserEntity> list = userRepository.findAllByOccupation(occupation, pageable);
        Page<UserEntityDTO> sorted = new PageImpl<>(list.stream()
                .map(this::userEntityToUserEntityDto)
                .collect(Collectors.toList()), pageable, list.getTotalElements());
        return sorted;
    }

    public Page<UserEntityDTO> findPaginatedDto(int pageNo, int pageSize,String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        Page<UserEntity> list = userRepository.findAllByRoles(RoleOfUsers.ROLE_MASTER, pageable);
        return new PageImpl<>(list.stream()
                .map(this::userEntityToUserEntityDto)
                .collect(Collectors.toList()), pageable, list.getTotalElements());
    }


    private UserEntityDTO userEntityToUserEntityDto(UserEntity userEntity) {
        return UserEntityDTO.builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .roles(userEntity.getRoles())
                .occupation(userEntity.getOccupation().getOcupation())
                .money(userEntity.getMoney())
                .averageRating(userEntity.getRatings().stream()
                        .mapToDouble(rating -> rating.getRating())
                        .average()
                        .orElse(0.0))
                .build();
    }
}
