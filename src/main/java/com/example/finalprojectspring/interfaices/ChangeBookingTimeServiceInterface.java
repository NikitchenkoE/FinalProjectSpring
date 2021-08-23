package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.entities.ScheduleEntity;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import org.springframework.transaction.annotation.Transactional;

public interface ChangeBookingTimeServiceInterface {
    @Transactional
    boolean setFreeFirstHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    @Transactional
    boolean setFreeSecondHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    @Transactional
    boolean setFreeThirdHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    @Transactional
    boolean setFreeForthHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException;

    ScheduleDTO findById(Long id);
}
