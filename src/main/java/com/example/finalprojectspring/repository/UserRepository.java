package com.example.finalprojectspring.repository;

import com.example.finalprojectspring.entities.RoleOfUsers;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    Page<UserEntity> findAllByRoles(RoleOfUsers role, Pageable pageable);

    Page<UserEntity> findAllByRoles(RoleOfUsers role, Pageable pageable, String sortField, String sortDirection);

    @Query("select u from UserEntity u join u.occupation o where o.ocupation=?1")
    Page<UserEntity> findAllByOccupation(String occupation, Pageable pageable);

    @Query("select u from UserEntity u join u.occupation o where o.ocupation=?1")
    Page<UserEntity> findAllByOccupation(String occupation, Pageable pageable, String sortField, String sortDirection);

    Page<UserEntity> findAll(Pageable pageable);

    void deleteByEmail(String email);


}
