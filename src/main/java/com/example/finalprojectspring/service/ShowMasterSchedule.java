package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDTO;
import com.example.finalprojectspring.entities.ScheduleEntity;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.ShowMasterScheduleInterface;
import com.example.finalprojectspring.mailService.EmailInterface;
import com.example.finalprojectspring.repository.ScheduleRepository;
import com.example.finalprojectspring.repository.UserRepository;
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
    private final EmailInterface emailInterface;
    private final UserRepository userRepository;


    @Autowired
    public ShowMasterSchedule(ScheduleRepository scheduleRepository, EmailInterface emailInterface, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.emailInterface = emailInterface;
        this.userRepository = userRepository;
    }

    public Page<ScheduleDTO> findPaginatedMasterSchedule(int pageNo, int pageSize, String masterEmail) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Page<ScheduleEntity> scheduleEntityPage = this.scheduleRepository.findAllByMasterEmail(masterEmail, pageable);
        return new PageImpl<>(scheduleEntityPage.stream()
                .map(this::scheduleEntityToScheduleDto)
                .collect(Collectors.toList()), pageable, scheduleEntityPage.getTotalElements());
    }

    @Transactional
    public void deleteScheduleDayById(ScheduleDTO scheduleDto){
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        UserEntity userEntityWithNeSchedule = userRepository.findByEmail(scheduleEntity.getMasterEmail());
        userEntityWithNeSchedule.getSchedule().remove(scheduleEntity);
        userRepository.save(userEntityWithNeSchedule);
        scheduleRepository.delete(scheduleEntity);
    }

    @Transactional
    public boolean setAsDoneFirstHour(ScheduleDTO scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setFirstHour(true);
        scheduleRepository.save(scheduleEntity);
        emailInterface.sendSimpleMessage(scheduleEntity.getClientEmailFirstHour(),
                "Mark",
                "http://localhost:8080/user/ratingMaster/"+scheduleEntity.getMasterEmail());
        return true;
    }

    @Transactional
    public boolean setAsDoneSecondHour(ScheduleDTO scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setSecondHour(true);
        scheduleRepository.save(scheduleEntity);
        emailInterface.sendSimpleMessage(scheduleEntity.getClientEmailSecondHour(),
                "Mark",
                "http://localhost:8080/user/ratingMaster/"+scheduleEntity.getMasterEmail());
        return true;
    }

    @Transactional
    public boolean setAsDoneThirdHour(ScheduleDTO scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setThirdHour(true);
        scheduleRepository.save(scheduleEntity);
        emailInterface.sendSimpleMessage(scheduleEntity.getClientEmailThirdHour(),
                "Mark",
                "http://localhost:8080/user/ratingMaster/"+scheduleEntity.getMasterEmail());
        return true;
    }

    @Transactional
    public boolean setAsDoneForthHour(ScheduleDTO scheduleDto){
        log.info("First hour set as done");
        ScheduleEntity scheduleEntity = scheduleRepository.findByID(scheduleDto.getId());
        scheduleEntity.setForthHour(true);
        scheduleRepository.save(scheduleEntity);
        emailInterface.sendSimpleMessage(scheduleEntity.getClientEmailForthHour(),
                "Mark",
                "http://localhost:8080/user/ratingMaster/"+scheduleEntity.getMasterEmail());
        return true;
    }



    private ScheduleDTO scheduleEntityToScheduleDto (ScheduleEntity scheduleEntity){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
                .withLocale(Locale.UK)
                .withZone(ZoneId.systemDefault());
        Timestamp timestamp = scheduleEntity.getWorkDay();
        String timeStampToString = dateTimeFormatter.format(timestamp.toInstant());
        return new ScheduleDTO().builder()
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


