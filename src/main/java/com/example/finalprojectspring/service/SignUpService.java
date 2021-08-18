package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.entities.ScheduleEntity;
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

    @Autowired
    public SignUpService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public ScheduleEntity setFirstHour(ScheduleDto scheduleDto) {
        log.info("client signUp first hour");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setFirstHour(false);
        scheduleEntity.setClientEmailFirstHour(scheduleDto.getClientEmailFirstHour());
        scheduleRepository.save(scheduleEntity);
        return scheduleEntity;
    }

    @Transactional
    public ScheduleEntity setSecondHour(ScheduleDto scheduleDto) {
        log.info("client signUp second hour");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setSecondHour(false);
        scheduleEntity.setClientEmailSecondHour(scheduleDto.getClientEmailSecondHour());
        scheduleRepository.save(scheduleEntity);
        return scheduleEntity;
    }

    @Transactional
    public ScheduleEntity setThirdHour(ScheduleDto scheduleDto) {
        log.info("client signUp third hour");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setThirdHour(false);
        scheduleEntity.setClientEmailThirdHour(scheduleDto.getClientEmailThirdHour());
        scheduleRepository.save(scheduleEntity);
        return scheduleEntity;
    }

    @Transactional
    public ScheduleEntity setForthHour(ScheduleDto scheduleDto) {
        log.info("client signUp forth hour");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setForthHour(false);
        scheduleEntity.setClientEmailForthHour(scheduleDto.getClientEmailForthHour());
        scheduleRepository.save(scheduleEntity);
        return scheduleEntity;
    }

    public boolean firstHourAlreadyBooked(ScheduleDto scheduleDto){
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present= Optional.ofNullable(scheduleEntity.getClientEmailFirstHour());
        return present.isPresent();
    }

    public boolean secondHourAlreadyBooked(ScheduleDto scheduleDto){
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present= Optional.ofNullable(scheduleEntity.getClientEmailSecondHour());
        return present.isPresent();
    }

    public boolean thirdHourAlreadyBooked(ScheduleDto scheduleDto){
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present= Optional.ofNullable(scheduleEntity.getClientEmailThirdHour());
        return present.isPresent();
    }

    public boolean forthHourAlreadyBooked(ScheduleDto scheduleDto){
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        Optional<String> present= Optional.ofNullable(scheduleEntity.getClientEmailForthHour());
        return present.isPresent();
    }

    public Page<ScheduleDto> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ScheduleEntity> scheduleEntityPage = this.scheduleRepository.findAllByMasterEmail(masterEmail, pageable);
        Page<ScheduleDto> sorted = new PageImpl<>(scheduleEntityPage.stream()
                .map(this::scheduleEntityToScheduleDto)
                .collect(Collectors.toList()), pageable, scheduleEntityPage.getTotalElements());
        return sorted;
    }

    private ScheduleDto scheduleEntityToScheduleDto(ScheduleEntity scheduleEntity) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(Locale.UK)
                .withZone(ZoneId.systemDefault());
        Timestamp timestamp = scheduleEntity.getWorkDay();
        String timeStampToString = dateTimeFormatter.format(timestamp.toInstant());
        ScheduleDto scheduleDto = new ScheduleDto().builder()
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
        return scheduleDto;
    }


}
