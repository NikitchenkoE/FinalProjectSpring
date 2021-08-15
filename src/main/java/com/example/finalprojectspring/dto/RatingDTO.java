package com.example.finalprojectspring.dto;

import com.example.finalprojectspring.entities.Rating;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RatingDTO{

    @NonNull
    private double ratingDto;
}
