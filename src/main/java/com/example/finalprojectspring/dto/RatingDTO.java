package com.example.finalprojectspring.dto;

import lombok.*;

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
