package com.example.finalprojectspring.repository;

import com.example.finalprojectspring.entities.MasterClient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MasterClientRepository extends JpaRepository<MasterClient,Long> {
}
