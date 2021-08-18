package com.example.finalprojectspring.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ScheduleDto {

    Long id;
    @NotNull
    private String workDay;

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
