package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.entities.ScheduleEntity;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.SetScheduleToMasterServiceInterface;
import com.example.finalprojectspring.repository.ScheduleRepository;
import com.example.finalprojectspring.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Log4j
public class SetScheduleToMasterService implements SetScheduleToMasterServiceInterface {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public SetScheduleToMasterService(UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public boolean setWorkDayToMaster(ScheduleDto scheduleDto) throws ParseException {
        log.info("added date");
        UserEntity userEntity = userRepository.findByEmail(scheduleDto.getMasterEmail());
        userEntity.getSchedule().add(convertToScheduleEntity(scheduleDto));
        userRepository.save(userEntity);
        return true;
    }


    public boolean dateToMasterPresentInDb(ScheduleDto scheduleDto) throws ParseException {
        Optional<List<ScheduleEntity>> scheduleEntity =
                Optional.ofNullable(scheduleRepository.findAllByMasterEmail(scheduleDto.getMasterEmail()));
        if (!scheduleEntity.isPresent()) {
            return false;
        }
        String dateDto = scheduleDto.getWorkDay();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateDto);
        Timestamp timestamp = new Timestamp(date.getTime());
        List<ScheduleEntity> notNullList = scheduleRepository.findAllByMasterEmail(scheduleDto.getMasterEmail());
        Stream<Boolean> booleanStream = notNullList.stream()
                .map(scheduleEntity1 -> scheduleEntity1.getWorkDay().equals(timestamp));
        Optional<Boolean> trueValue = booleanStream.
                filter(aBoolean -> aBoolean.equals(true)).
                findFirst();
        return trueValue.isPresent();
    }


    public ScheduleEntity convertToScheduleEntity(ScheduleDto scheduleDto) throws ParseException {
        String dateDto = scheduleDto.getWorkDay();
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateDto);
        Timestamp timestamp = new Timestamp(date.getTime());
        ScheduleEntity scheduleEntity = new ScheduleEntity().builder()
                .workDay(timestamp)
                .masterEmail(scheduleDto.getMasterEmail())
                .firstHour(scheduleDto.getFirstHour())
                .secondHour(scheduleDto.getSecondHour())
                .thirdHour(scheduleDto.getThirdHour())
                .forthHour(scheduleDto.getForthHour())
                .build();
        return scheduleEntity;
    }


}
