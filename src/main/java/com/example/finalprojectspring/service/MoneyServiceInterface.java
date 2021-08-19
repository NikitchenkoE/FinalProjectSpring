package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDtoMoney;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import org.springframework.transaction.annotation.Transactional;

public interface MoneyServiceInterface {
    @Transactional
    boolean replenishMoneyAccount(UserEntityDtoMoney userEntityDtoMoney);

    @Transactional
    boolean sentMoneyToAnotherUser(double sum, String receiverEmail, String senderEmail) throws NotEnoughMoneyException;

    UserEntityDtoMoney showUserByEmail(String email);
}
