package com.example.finalprojectspring.repository;

import com.example.finalprojectspring.entities.MasterOcupationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterOccupationRepository extends JpaRepository<MasterOcupationEntity, Long> {
}
