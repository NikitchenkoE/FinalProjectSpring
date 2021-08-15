package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.RatingDTO;
import com.example.finalprojectspring.entities.Rating;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.interfaices.InterfaceUserMarkService;
import com.example.finalprojectspring.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j
public class UserMarkService implements InterfaceUserMarkService {

    private final UserRepository userRepository;

    @Autowired
    public UserMarkService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void sentMarkToMaster(RatingDTO ratingDTO,String email){
        log.info("Added mark to master");
        UserEntity userEntity = userRepository.findByEmail(email);
        List<Rating> listRating = userEntity.getRatings();
        Rating rating = new Rating();
        rating.setRating(ratingDTO.getRatingDto());
        listRating.add(rating);
        userEntity.setRatings(listRating);
        userRepository.save(userEntity);
    }
}
