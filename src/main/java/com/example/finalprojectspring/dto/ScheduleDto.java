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
    @NotNull
    private String workDay;

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
