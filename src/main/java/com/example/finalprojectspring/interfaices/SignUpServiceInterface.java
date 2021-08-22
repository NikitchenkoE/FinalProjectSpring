package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import org.springframework.data.domain.Page;

public interface SignUpServiceInterface {

    boolean setFirstHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    boolean setSecondHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    boolean setThirdHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    boolean setForthHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    Page<ScheduleDTO> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail);

    boolean firstHourAlreadyBooked(ScheduleDTO scheduleDto);
    boolean secondHourAlreadyBooked(ScheduleDTO scheduleDto);
    boolean thirdHourAlreadyBooked(ScheduleDTO scheduleDto);
    boolean forthHourAlreadyBooked(ScheduleDTO scheduleDto);

}
