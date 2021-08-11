package com.example.finalprojectspring.repository;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    Page<UserEntity> findAllByRoles(Role_Of_Users role, Pageable pageable);

    Page<UserEntity> findAll(Pageable pageable);

    void deleteByEmail(String email);

    List<UserEntity> findAllByOccupation(String occupation);


}
