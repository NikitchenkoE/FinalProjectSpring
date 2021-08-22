package com.example.finalprojectspring.repository;

import com.example.finalprojectspring.entities.ScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByMasterEmail(String email);

    Page<ScheduleEntity> findAllByMasterEmail(String email, Pageable pageable);

    ScheduleEntity findByID(Long id);
}
