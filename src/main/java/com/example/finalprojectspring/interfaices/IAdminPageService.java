package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.ScheduleDto;
import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdminPageService {

    void deleteUserByEmail(String email);

    UserEntity createNewMaster(UserEntityDTO userEntityDTO);

    boolean userPresentInDb(UserEntityDTO userEntityDTO);

    Page<UserEntity> findPaginatedMaster(int pageNo, int pageSize);

    Page<UserEntity> findPaginatedUser(int pageNo, int pageSize);

}
