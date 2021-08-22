package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDTO;

import java.text.ParseException;

public interface SetScheduleToMasterServiceInterface {
    boolean setWorkDayToMaster(ScheduleDTO scheduleDto) throws ParseException;

    boolean dateToMasterPresentInDb(ScheduleDTO scheduleDto) throws ParseException;
}
