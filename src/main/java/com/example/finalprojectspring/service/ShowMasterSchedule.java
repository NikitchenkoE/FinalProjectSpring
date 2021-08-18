package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.entities.ScheduleEntity;
import com.example.finalprojectspring.interfaices.ShowMasterScheduleInterface;
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
import java.util.stream.Collectors;

@Service
@Log4j
public class ShowMasterSchedule implements ShowMasterScheduleInterface {
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ShowMasterSchedule(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Page<ScheduleDto> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ScheduleEntity> scheduleEntityPage = this.scheduleRepository.findAllByMasterEmail(masterEmail, pageable);
        Page<ScheduleDto> sorted = new PageImpl<>(scheduleEntityPage.stream()
                .map(this::scheduleEntityToScheduleDto)
                .collect(Collectors.toList()), pageable, scheduleEntityPage.getTotalElements());
        return sorted;
    }

    @Transactional
    public void deleteScheduleDayById(ScheduleDto scheduleDto){
        scheduleRepository.removeByID(scheduleDto.getId());

    }

    @Transactional
    public boolean setAsDoneFirstHour(ScheduleDto scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setFirstHour(true);
        scheduleRepository.save(scheduleEntity);
        return true;
    }

    @Transactional
    public boolean setAsDoneSecondHour(ScheduleDto scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setSecondHour(true);
        scheduleRepository.save(scheduleEntity);
        return true;
    }

    @Transactional
    public boolean setAsDoneThirdHour(ScheduleDto scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setThirdHour(true);
        scheduleRepository.save(scheduleEntity);
        return true;
    }

    @Transactional
    public boolean setAsDoneForthHour(ScheduleDto scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setForthHour(true);
        scheduleRepository.save(scheduleEntity);
        return true;
    }



    private ScheduleDto scheduleEntityToScheduleDto (ScheduleEntity scheduleEntity){
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


