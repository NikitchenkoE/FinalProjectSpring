package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;

public interface IAdminPageService {

    void deleteUserByEmail(String email);

    boolean createNewMaster(UserEntityDTO userEntityDTO);

    boolean userPresentInDb(UserEntityDTO userEntityDTO);

    Page<UserEntity> findPaginatedMaster(int pageNo, int pageSize);

    Page<UserEntity> findPaginatedUser(int pageNo, int pageSize);

}
