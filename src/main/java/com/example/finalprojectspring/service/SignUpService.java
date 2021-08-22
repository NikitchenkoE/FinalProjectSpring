package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.entities.ScheduleEntity;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import com.example.finalprojectspring.interfaices.MoneyServiceInterface;
import com.example.finalprojectspring.interfaices.SignUpServiceInterface;
import com.example.finalprojectspring.repository.ScheduleRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j
public class SignUpService implements SignUpServiceInterface {

    private final ScheduleRepository scheduleRepository;
    private final MoneyServiceInterface moneyService;

    @Autowired
    public SignUpService(ScheduleRepository scheduleRepository, MoneyServiceInterface moneyService) {
        this.scheduleRepository = scheduleRepository;
        this.moneyService = moneyService;
    }

    @Transactional
    public boolean setFirstHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client signUp first hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,"admin@gmail.com",scheduleDto.getClientEmailFirstHour());
        if (paymentSuccessful) {
            ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
            scheduleEntity.setFirstHour(false);
            scheduleEntity.setClientEmailFirstHour(scheduleDto.getClientEmailFirstHour());
            scheduleRepository.save(scheduleEntity);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean setSecondHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client signUp second hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,"admin@gmail.com",scheduleDto.getClientEmailSecondHour());
        if (paymentSuccessful) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setSecondHour(false);
        scheduleEntity.setClientEmailSecondHour(scheduleDto.getClientEmailSecondHour());
        scheduleRepository.save(scheduleEntity);
        return true;
        }
        return false;
    }

    @Transactional
    public boolean setThirdHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client signUp third hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,"admin@gmail.com",scheduleDto.getClientEmailThirdHour());
        if (paymentSuccessful) {
            ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
            scheduleEntity.setThirdHour(false);
            scheduleEntity.setClientEmailThirdHour(scheduleDto.getClientEmailThirdHour());
            scheduleRepository.save(scheduleEntity);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean setForthHour(ScheduleDTO scheduleDto) throws NotEnoughMoneyException {
        log.info("client signUp forth hour");
        boolean paymentSuccessful = moneyService.sentMoneyToAnotherUser(500,"admin@gmail.com",scheduleDto.getClientEmailForthHour());
        if (paymentSuccessful) {
            ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
            scheduleEntity.setForthHour(false);
            scheduleEntity.setClientEmailForthHour(scheduleDto.getClientEmailForthHour());
            scheduleRepository.save(scheduleEntity);
            return true;
        }
        return false;
    }

    public boolean firstHourAlreadyBooked(ScheduleDTO scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present = Optional.ofNullable(scheduleEntity.getClientEmailFirstHour());
        return present.isPresent();
    }

    public boolean secondHourAlreadyBooked(ScheduleDTO scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present = Optional.ofNullable(scheduleEntity.getClientEmailSecondHour());
        return present.isPresent();
    }

    public boolean thirdHourAlreadyBooked(ScheduleDTO scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present = Optional.ofNullable(scheduleEntity.getClientEmailThirdHour());
        return present.isPresent();
    }

    public boolean forthHourAlreadyBooked(ScheduleDTO scheduleDto) {
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present = Optional.ofNullable(scheduleEntity.getClientEmailForthHour());
        return present.isPresent();
    }

    public Page<ScheduleDTO> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ScheduleEntity> scheduleEntityPage = this.scheduleRepository.findAllByMasterEmail(masterEmail, pageable);
        return new PageImpl<>(scheduleEntityPage.stream()
                .map(this::scheduleEntityToScheduleDto)
                .collect(Collectors.toList()), pageable, scheduleEntityPage.getTotalElements());
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
