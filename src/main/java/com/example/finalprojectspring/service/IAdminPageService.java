package com.example.finalprojectspring.service;

import com.example.finalprojectspring.entities.UserEntity;

import java.util.List;

public interface IAdminPageService {
    public List<UserEntity> findAll();
    public List<UserEntity> findAllwithRoleUser();
    public void deleteUserByEmail(String email);
}
