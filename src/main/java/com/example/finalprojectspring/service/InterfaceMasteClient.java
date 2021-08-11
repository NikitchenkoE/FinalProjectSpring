package com.example.finalprojectspring.service;

import com.example.finalprojectspring.entities.MasterClient;
import com.example.finalprojectspring.entities.UserEntity;

import java.util.Date;

public interface InterfaceMasteClient {
     public UserEntity addNewClient(UserEntity userEntity, MasterClient masterClient);
     public UserEntity findUserInDbByEmail(String email);
}
