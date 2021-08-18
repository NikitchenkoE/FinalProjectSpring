package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SignUpServiceTest {

    @Autowired
    private SignUpService signUpService;

    @Test
    void setFirstHour() {
        ScheduleDto scheduleDto = new ScheduleDto().builder()
                .id(328L)
                .clientEmailFirstHour("testClient")
                .build();
        signUpService.setFirstHour(scheduleDto);
    }

    @Test
    void setSecondHour() {
    }

    @Test
    void setThirdHour() {
    }

    @Test
    void setForthHour() {
    }
}