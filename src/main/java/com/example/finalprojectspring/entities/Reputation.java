package com.example.finalprojectspring.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reputation {

    @Id
    @GeneratedValue
    private Long id;

    private double reputation;

}
