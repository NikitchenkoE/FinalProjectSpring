package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationServiceTest {

    private RegistrationService registrationService;
    private UserRepository repository;

    @Autowired
    public RegistrationServiceTest(RegistrationService registrationService, UserRepository repository) {
        this.registrationService = registrationService;
        this.repository = repository;
    }

    @Test
    void addUserToDataBase() {
        UserEntityDTO userEntityDTO = new UserEntityDTO().builder()
                .email("testmailreg@gmail.com")
                .password("testpassword")
                .firstName("test")
                .lastName("test")
                .roles(Collections.singleton(Role_Of_Users.ROLE_USER))
                .build();
        registrationService.addUserToDataBase(userEntityDTO);
        UserEntity userEntity = repository.findByEmail("testmail@gmail.com");
        String userEntityEmail = userEntity.getEmail();

        assertThat(userEntityEmail).isEqualTo("testmail@gmail.com");
    }

    @Test
    void userPresentInDb() {
        UserEntityDTO userEntityDTO = new UserEntityDTO().builder()
                .email("admin@gmail.com")
                .password("testpassword")
                .firstName("test")
                .lastName("test")
                .roles(Collections.singleton(Role_Of_Users.ROLE_ADMIN))
                .occupation("testMaster")
                .build();
        Boolean usrPresent = registrationService.userPresentInDb(userEntityDTO);
        assertThat(usrPresent).isTrue();
    }
}