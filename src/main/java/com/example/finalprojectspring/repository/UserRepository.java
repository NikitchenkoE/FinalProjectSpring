package com.example.finalprojectspring.repository;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);
    public List<UserEntity> findAllByRoles (Role_Of_Users role);
    public void deleteByEmail(String email);
}
