package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IGeneraPageService {
    public Page<UserEntity> showAllMasters(Pageable pageable);
    Page<UserEntity> findPaginated(int pageNo, int pageSize);
}
