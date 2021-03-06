package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.MasterOcupationEntity;
import com.example.finalprojectspring.entities.RoleOfUsers;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.exeption.ApiRequestExeption;
import com.example.finalprojectspring.interfaices.IAdminPageService;
import com.example.finalprojectspring.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j
public class AdminPageService implements IAdminPageService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    public AdminPageService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @Transactional
    public void deleteUserByEmail(String email) {
        log.info("user deleted");
        final UserEntity existenceUserByEmail =
                Optional.ofNullable(userRepository.findByEmail(email))
                        .orElseThrow(() -> new ApiRequestExeption("This user do not exist or already deleted"));
        userRepository.delete(existenceUserByEmail);
    }

    @Transactional
    public boolean createNewMaster(UserEntityDTO userEntityDTO) {
        log.info("Added new master");
        MasterOcupationEntity occupation = new MasterOcupationEntity();
        occupation.setOcupation(userEntityDTO.getOccupation());
        UserEntity userEntity = UserEntity.builder()
                .email(userEntityDTO.getEmail())
                .password(passwordEncoder.encode(userEntityDTO.getPassword()))
                .firstName(userEntityDTO.getFirstName())
                .lastName(userEntityDTO.getLastName())
                .roles(userEntityDTO.getRoles())
                .occupation(occupation)
                .money(userEntityDTO.getMoney())
                .build();

        userRepository.save(userEntity);
        return true;
    }

    public boolean userPresentInDb(UserEntityDTO userEntityDTO) {
        log.error("User present in db");
        Optional<UserEntity> existenceUserByEmail =
                Optional.ofNullable(userRepository.findByEmail(userEntityDTO.getEmail()));
        return existenceUserByEmail.isPresent();
    }

    public Page<UserEntity> findPaginatedMaster(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepository.findAllByRoles(RoleOfUsers.ROLE_MASTER, pageable);
    }

    public Page<UserEntity> findPaginatedUser(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.userRepository.findAllByRoles(RoleOfUsers.ROLE_USER, pageable);
    }


}
