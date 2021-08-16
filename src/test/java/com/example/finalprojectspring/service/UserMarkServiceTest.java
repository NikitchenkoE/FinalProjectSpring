package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.RatingDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserMarkServiceTest {

    @Autowired
    private UserMarkService markService;
    @Test
    void sentMarkToMaster() {
        RatingDTO ratingDTO = new RatingDTO().builder().ratingDto(5.0).build();
        Boolean markedAsSent = markService.sentMarkToMaster(ratingDTO,"master2@gmail.com");
        assertThat(markedAsSent).isTrue();
    }
}