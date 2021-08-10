package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdminPageService {

    public void deleteUserByEmail(String email);

    public UserEntity createNewMaster(UserEntityDTO userEntityDTO);

    public boolean userPresentInDb(UserEntityDTO userEntityDTO);

    public Page<UserEntity> findPaginatedMaster(int pageNo, int pageSize);

    public Page<UserEntity> findPaginatedUser(int pageNo, int pageSize);
}
