package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;

public interface IGeneraPageService {
    Page<UserEntity> findPaginated(int pageNo, int pageSize, String occupation);

    Page<UserEntity> findPaginated(int pageNo, int pageSize);

}
