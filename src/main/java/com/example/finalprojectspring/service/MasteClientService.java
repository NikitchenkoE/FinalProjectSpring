package com.example.finalprojectspring.service;

import com.example.finalprojectspring.entities.MasterClient;
import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class MasteClientService implements InterfaceMasteClient {

    private final UserRepository userRepository;

    public MasteClientService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity addNewClient(UserEntity userEntity,MasterClient masterClient){
        userEntity.setClients(Arrays.asList(masterClient));
        return userEntity;
    }

    public UserEntity findUserInDbByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
