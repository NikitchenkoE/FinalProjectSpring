package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.entities.ScheduleEntity;
import org.springframework.data.domain.Page;

public interface SignUpServiceInterface {

    ScheduleEntity setFirstHour(ScheduleDto scheduleDto);

    ScheduleEntity setSecondHour(ScheduleDto scheduleDto);

    ScheduleEntity setThirdHour(ScheduleDto scheduleDto);

    ScheduleEntity setForthHour(ScheduleDto scheduleDto);

    Page<ScheduleDto> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail);

    boolean firstHourAlreadyBooked(ScheduleDto scheduleDto);
    boolean secondHourAlreadyBooked(ScheduleDto scheduleDto);
    boolean thirdHourAlreadyBooked(ScheduleDto scheduleDto);
    boolean forthHourAlreadyBooked(ScheduleDto scheduleDto);

}
