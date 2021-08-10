package com.example.finalprojectspring.interfaices;

import com.example.finalprojectspring.dto.UserEntityDTO;
import com.example.finalprojectspring.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdminPageService {
    public List<UserEntity> findAll();

    public Page<UserEntity> findAllwithRoleUser(Pageable pageable);

    public void deleteUserByEmail(String email);

    public UserEntity createNewMaster(UserEntityDTO userEntityDTO);

    public boolean userPresentInDb(UserEntityDTO userEntityDTO);

    public Page<UserEntity> findAllWithRoleMaster(Pageable pageable);

    Page<UserEntity> findPaginated(int pageNo, int pageSize);
}
