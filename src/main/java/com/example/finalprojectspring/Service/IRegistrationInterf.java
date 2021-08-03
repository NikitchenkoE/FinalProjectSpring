package com.example.finalprojectspring.Service;

import com.example.finalprojectspring.DTO.UserEntityDTO;
import com.example.finalprojectspring.Entities.UserEntity;

public interface IRegistrationInterf {
    UserEntity addUserToDataBase(UserEntityDTO userEntityDTO);
}
