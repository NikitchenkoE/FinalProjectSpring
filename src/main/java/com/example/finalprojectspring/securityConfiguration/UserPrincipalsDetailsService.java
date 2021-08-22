package com.example.finalprojectspring.securityConfiguration;

import com.example.finalprojectspring.entities.UserEntity;
import com.example.finalprojectspring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserPrincipalsDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalsDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NotNull String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        UserPrincipal userPrincipal = new UserPrincipal(userEntity);
        return userPrincipal;
    }
}
