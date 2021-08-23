package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.UserEntityDTO;
import org.springframework.data.domain.Page;

public interface IGeneraPageService {
    Page<UserEntityDTO> findPaginated(int pageNo, int pageSize, String occupation,String sortField, String sortDirection);

    Page<UserEntityDTO> findPaginatedDto(int pageNo, int pageSize,String sortField, String sortDirection);

}
