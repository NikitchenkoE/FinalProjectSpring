package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDto;
import org.springframework.data.domain.Page;

public interface ShowMasterScheduleInterface {
    Page<ScheduleDto> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail);

    void deleteScheduleDayById(ScheduleDto scheduleDto);

    boolean setAsDoneFirstHour(ScheduleDto scheduleDto);

    boolean setAsDoneSecondHour(ScheduleDto scheduleDto);

    boolean setAsDoneThirdHour(ScheduleDto scheduleDto);

    boolean setAsDoneForthHour(ScheduleDto scheduleDto);
}
