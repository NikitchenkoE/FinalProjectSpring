package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.MoneyDTO;
import com.example.finalprojectspring.exeption.NotEnoughMoneyException;
import org.springframework.transaction.annotation.Transactional;

public interface MoneyServiceInterface {
    @Transactional
    boolean replenishMoneyAccount(MoneyDTO moneyDTO);

    @Transactional
    boolean sentMoneyToAnotherUser(double sum, String receiverEmail, String senderEmail) throws NotEnoughMoneyException;

    MoneyDTO showUserByEmail(String email);
}
