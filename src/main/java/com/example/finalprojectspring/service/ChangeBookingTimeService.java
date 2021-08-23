package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.entities.ScheduleEntity;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import com.example.finalprojectspring.interfaices.ChangeBookingTimeServiceInterface;
import com.example.finalprojectspring.interfaices.MoneyServiceInterface;
import com.example.finalprojectspring.repository.ScheduleRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Service
@Log4j
public class ChangeBookingTimeService implements ChangeBookingTimeServiceInterface {

    private final ScheduleRepository scheduleRepository;
    private final MoneyServiceInterface moneyService;

    @Autowired
    public ChangeBookingTimeService(ScheduleRepository scheduleRepository, MoneyServiceInterface moneyService) {
        this.scheduleRepository = scheduleRepository;
        this.moneyService = moneyService;
    }

    @Override
    @Transactional
    public boolean setFreeFirstHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client canceled first hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,scheduleDto.getClientEmailFirstHour(),"admin@gmail.com");
        if (paymentSuccessful) {
            ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
            scheduleEntity.setFirstHour(true);
            scheduleEntity.setClientEmailFirstHour(null);
            scheduleRepository.save(scheduleEntity);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean setFreeSecondHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client canceled first hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,scheduleDto.getClientEmailSecondHour(),"admin@gmail.com");
        if (paymentSuccessful) {
            ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
            scheduleEntity.setSecondHour(true);
            scheduleEntity.setClientEmailSecondHour(null);
            scheduleRepository.save(scheduleEntity);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean setFreeThirdHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client canceled first hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,scheduleDto.getClientEmailThirdHour(),"admin@gmail.com");
        if (paymentSuccessful) {
            ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
            scheduleEntity.setThirdHour(true);
            scheduleEntity.setClientEmailThirdHour(null);
            scheduleRepository.save(scheduleEntity);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean setFreeForthHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client canceled first hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,scheduleDto.getClientEmailForthHour(),"admin@gmail.com");
        if (paymentSuccessful) {
            ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
            scheduleEntity.setForthHour(true);
            scheduleEntity.setClientEmailForthHour(null);
            scheduleRepository.save(scheduleEntity);
            return true;
        }
        return false;
    }

    public ScheduleDTO findById(Long id){
        return scheduleEntityToScheduleDto(scheduleRepository.findByID(id));
    }

    private ScheduleDTO scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(Locale.UK)
                .withZone(ZoneId.systemDefault());
        Timestamp timestamp = scheduleEntity.getWorkDay();
        String timeStampToString = dateTimeFormatter.format(timestamp.toInstant());
        return ScheduleDTO.builder()
                .id(scheduleEntity.getID())
                .masterEmail(scheduleEntity.getMasterEmail())
                .workDay(timeStampToString)
                .clientEmailFirstHour(scheduleEntity.getClientEmailFirstHour())
                .clientEmailSecondHour(scheduleEntity.getClientEmailSecondHour())
                .clientEmailThirdHour(scheduleEntity.getClientEmailThirdHour())
                .clientEmailForthHour(scheduleEntity.getClientEmailForthHour())
                .firstHour(scheduleEntity.getFirstHour())
                .secondHour(scheduleEntity.getSecondHour())
                .thirdHour(scheduleEntity.getThirdHour())
                .forthHour(scheduleEntity.getForthHour())
                .build();
    }



}
