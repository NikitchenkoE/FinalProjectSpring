package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.RatingDTO;

public interface InterfaceUserMarkService {
    boolean sentMarkToMaster(RatingDTO ratingDTO, String email);
}
