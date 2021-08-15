package com.example.finalprojectspring.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class ScheduleEntity{

    @Id
    private Long ID;

    private Date workDay;

    private String emailClient;
    private String firstNameClient;
    private String lastNameClient;

    private Boolean firstHour;
    private Boolean secondHour;
    private Boolean thirdHour;
    private Boolean forthHour;






}
