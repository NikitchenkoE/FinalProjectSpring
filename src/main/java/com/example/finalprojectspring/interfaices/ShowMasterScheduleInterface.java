package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDTO;
import org.springframework.data.domain.Page;

public interface ShowMasterScheduleInterface {
    Page<ScheduleDTO> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail);

    void deleteScheduleDayById(ScheduleDTO scheduleDto);

    boolean setAsDoneFirstHour(ScheduleDTO scheduleDto);

    boolean setAsDoneSecondHour(ScheduleDTO scheduleDto);

    boolean setAsDoneThirdHour(ScheduleDTO scheduleDto);

    boolean setAsDoneForthHour(ScheduleDTO scheduleDto);
}
