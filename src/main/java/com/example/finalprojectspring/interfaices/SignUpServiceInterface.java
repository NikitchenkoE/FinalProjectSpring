package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.entities.ScheduleEntity;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import org.springframework.data.domain.Page;

public interface SignUpServiceInterface {

    boolean setFirstHour(ScheduleDto scheduleDto) throws NotEnoughMoneyException;

    boolean setSecondHour(ScheduleDto scheduleDto) throws NotEnoughMoneyException;

    boolean setThirdHour(ScheduleDto scheduleDto) throws NotEnoughMoneyException;

    boolean setForthHour(ScheduleDto scheduleDto) throws NotEnoughMoneyException;

    Page<ScheduleDto> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail);

    boolean firstHourAlreadyBooked(ScheduleDto scheduleDto);
    boolean secondHourAlreadyBooked(ScheduleDto scheduleDto);
    boolean thirdHourAlreadyBooked(ScheduleDto scheduleDto);
    boolean forthHourAlreadyBooked(ScheduleDto scheduleDto);

}
