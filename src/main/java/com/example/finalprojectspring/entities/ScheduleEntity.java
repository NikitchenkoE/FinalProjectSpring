package com.example.finalprojectspring.entities;

import lombok.*;

import javax.persistence.Entity;
import java.sql.Timestamp;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class ScheduleEntity extends BaseEntity {


    private Timestamp workDay;

    private String masterEmail;

    private String clientEmailFirstHour;
    private String clientEmailSecondHour;
    private String clientEmailThirdHour;
    private String clientEmailForthHour;

    private Boolean firstHour;
    private Boolean secondHour;
    private Boolean thirdHour;
    private Boolean forthHour;



}
