package com.example.finalprojectspring.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Rating {
    @Id
    Long id;

    double rating;
}
