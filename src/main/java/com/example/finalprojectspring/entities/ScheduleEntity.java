package com.example.finalprojectspring.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Date;


@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    private Timestamp workDay;

    private String masterEmail;

    private Long clientIdFirstHour;
    private Long clientIdSecondHour;
    private Long clientIdThirdHour;
    private Long clientIdForthHour;

    private Boolean firstHour;
    private Boolean secondHour;
    private Boolean thirdHour;
    private Boolean forthHour;

}
