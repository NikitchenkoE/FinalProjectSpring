package com.example.finalprojectspring.repository;

import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByEmail(String email);
    public UserEntity findAllByRoles (String role);
}
