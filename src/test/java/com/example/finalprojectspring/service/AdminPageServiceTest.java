package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminPageServiceTest {

    @Autowired
    private AdminPageService adminPageService;

    @Autowired
    private UserRepository repository;



    @Test
    void createNewMaster() {
        UserEntityDTO userEntityDTO = new UserEntityDTO().builder()
                .email("testmail@gmail.com")
                .password("testpassword")
                .firstName("test")
                .lastName("test")
                .roles(Collections.singleton(Role_Of_Users.ROLE_MASTER))
                .occupation("testMaster")
                .build();

        adminPageService.createNewMaster(userEntityDTO);

        UserEntity userEntity = repository.findByEmail("testmail@gmail.com");
        String userEntityEmail = userEntity.getEmail();

        assertThat(userEntityEmail).isEqualTo("testmail@gmail.com");
    }

    @Test
    void deleteUserByEmail() {
        Page page = repository.findAll(Pageable.unpaged());
        Long startNumberOfElements = page.getTotalElements();
        adminPageService.deleteUserByEmail("testmail@gmail.com");
        Long finishNumberOfElements = page.getTotalElements();
        assertThat(startNumberOfElements).isNotEqualTo(finishNumberOfElements);
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
        Boolean usrpresent = adminPageService.userPresentInDb(userEntityDTO);
        assertThat(usrpresent).isTrue();
    }


}