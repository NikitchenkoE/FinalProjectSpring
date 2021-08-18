package com.example.finalprojectspring.entities;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
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

    private String clientEmailFirstHour;
    private String clientEmailSecondHour;
    private String clientEmailThirdHour;
    private String clientEmailForthHour;

    private Boolean firstHour;
    private Boolean secondHour;
    private Boolean thirdHour;
    private Boolean forthHour;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

}
