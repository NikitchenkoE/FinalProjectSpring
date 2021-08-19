package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.dto.UserEntityDtoMoney;
import com.example.finalprojectspring.entities.Role_Of_Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class MoneyServiceTest {

    @Autowired
   private MoneyService moneyService;

    @Test
    void replenishMoneyAccount() {
        UserEntityDtoMoney userEntityDTO = new UserEntityDtoMoney().builder()
                .email("testmailreg@gmail.com")
                .firstName("test")
                .lastName("test")
                .roles(Collections.singleton(Role_Of_Users.ROLE_USER))
                .money(100000)
                .build();
       boolean moneyReplenished =  moneyService.replenishMoneyAccount(userEntityDTO);
        assertThat(moneyReplenished).isTrue();
    }

    @Test
    void sentMoneyToAnotherUser() {
    }
}