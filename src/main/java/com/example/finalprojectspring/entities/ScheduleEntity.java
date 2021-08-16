package com.example.finalprojectspring.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    private Date workDay;

    private String masterEmail;
    private Long clientId;

    private Boolean firstHour = new Boolean(true);
    private Boolean secondHour = new Boolean(true);
    private Boolean thirdHour = new Boolean(true);
    private Boolean forthHour = new Boolean(true);

}
