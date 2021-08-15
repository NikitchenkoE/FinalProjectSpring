package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;

public interface IGeneraPageService {
    Page<UserEntityDTO> findPaginated(int pageNo, int pageSize, String occupation);

    Page<UserEntityDTO> findPaginatedDto(int pageNo, int pageSize);

}
