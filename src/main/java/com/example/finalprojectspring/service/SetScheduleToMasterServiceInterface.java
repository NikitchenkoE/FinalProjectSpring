package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.dto.UserEntityDTO;

import java.text.ParseException;

public interface SetScheduleToMasterServiceInterface {
    boolean setWorkDayToMaster(ScheduleDto scheduleDto) throws ParseException;

    boolean dateToMasterPresentInDb(ScheduleDto scheduleDto) throws ParseException;
}
