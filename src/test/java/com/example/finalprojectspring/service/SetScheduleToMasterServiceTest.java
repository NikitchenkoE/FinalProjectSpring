package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class SetScheduleToMasterServiceTest {

    @Autowired
    private SetScheduleToMasterService scheduleToMasterService;

    @Test
    void setWorkDayToMasterTest() throws ParseException {
        String date ="2021-08-12";
        ScheduleDto scheduleDto = new ScheduleDto().builder()
                .workDay(date)
                .masterEmail("testmail@gmail.com")
                .forthHour(true)
                .secondHour(true)
                .thirdHour(true)
                .forthHour(true).build();
        Boolean test = scheduleToMasterService.setWorkDayToMaster(scheduleDto);
        assertThat(test).isTrue();
    }

    @Test
    void dateToMasterPresentInDb() throws ParseException{
        String date ="2021-08-12";
        ScheduleDto scheduleDto = new ScheduleDto().builder()
                .workDay(date)
                .masterEmail("testmail@gmail.com")
                .forthHour(true)
                .secondHour(true)
                .thirdHour(true)
                .forthHour(true).build();
        Boolean present = scheduleToMasterService.dateToMasterPresentInDb(scheduleDto);
        assertThat(present).isTrue();

    }
    @Test
    void dateToMasterNotPresentInDb() throws ParseException{
        String date ="2021-09-12";
        ScheduleDto scheduleDto = new ScheduleDto().builder()
                .workDay(date)
                .masterEmail("testmail@gmail.com")
                .forthHour(true)
                .secondHour(true)
                .thirdHour(true)
                .forthHour(true).build();
        Boolean present = scheduleToMasterService.dateToMasterPresentInDb(scheduleDto);
        assertThat(present).isFalse();

    }
}