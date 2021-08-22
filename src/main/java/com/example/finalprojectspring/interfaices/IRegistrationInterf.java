package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;

public interface IRegistrationInterf {
    UserEntity addUserToDataBase(UserEntityDTO userEntityDTO);

    boolean userPresentInDb(UserEntityDTO userEntityDTO);
}
