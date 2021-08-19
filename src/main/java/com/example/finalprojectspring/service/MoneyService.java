package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.dto.UserEntityDtoMoney;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoneyService implements  MoneyServiceInterface {
    private final UserRepository userRepository;

    @Autowired
    public MoneyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    @Transactional
    public boolean replenishMoneyAccount(UserEntityDtoMoney userEntityDtoMoney){
        UserEntity userEntity = userRepository.findByEmail(userEntityDtoMoney.getEmail());
        userEntity.setMoney(userEntity.getMoney() + userEntityDtoMoney.getMoney());
        userRepository.save(userEntity);
        return true;
    }

    @Override
    @Transactional
    public boolean sentMoneyToAnotherUser(double sum, String receiverEmail, String senderEmail) throws NotEnoughMoneyException {
        UserEntity userSender = userRepository.findByEmail(senderEmail);
        UserEntity userReceiver = userRepository.findByEmail(receiverEmail);
        double senderNewMoney = userSender.getMoney() - sum;

        if (senderNewMoney<0){
            throw new NotEnoughMoneyException("Sorry, you haven't enough money");
        }
        userSender.setMoney(senderNewMoney);
        userReceiver.setMoney(userReceiver.getMoney()+sum);
        userRepository.save(userSender);
        userRepository.save(userReceiver);
        return true;
    }

    @Override
    public UserEntityDtoMoney showUserByEmail(String email){
        UserEntity userEntity = userRepository.findByEmail(email);
        return userEntityToUserEntityDtoMoney(userEntity);
    }


    private UserEntityDtoMoney userEntityToUserEntityDtoMoney(UserEntity userEntity) {
        UserEntityDtoMoney userEntityDTOMoney = new UserEntityDtoMoney().builder()
                .email(userEntity.getEmail())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .roles(userEntity.getRoles())
                .averageRating(userEntity.getRatings().stream()
                        .mapToDouble(rating -> rating.getRating())
                        .average()
                        .orElse(0.0))
                .money(userEntity.getMoney())
                .build();
        return userEntityDTOMoney;
    }
}
