package com.example.finalprojectspring.entities;

import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MasterClient {

    @Id
    @GeneratedValue
    Long id;

    private String clientEmail;

    @UniqueElements
    @DateTimeFormat(pattern = "dd/MM/yyyy h:mm a")
    private Date date;

}
