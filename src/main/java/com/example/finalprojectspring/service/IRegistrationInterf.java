package com.example.finalprojectspring.service;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;

public interface IRegistrationInterf {
    UserEntity addUserToDataBase(UserEntityDTO userEntityDTO);
    public boolean userPresentInDb(UserEntityDTO userEntityDTO);
}
