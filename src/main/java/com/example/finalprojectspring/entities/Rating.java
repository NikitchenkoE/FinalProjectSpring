package com.example.finalprojectspring.entities;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rating extends BaseEntity {


    private double rating;
}
