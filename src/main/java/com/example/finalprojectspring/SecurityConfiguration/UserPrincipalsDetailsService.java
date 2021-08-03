package com.example.finalprojectspring.SecurityConfiguration;

import com.example.finalprojectspring.Entities.UserEntity;
import com.example.finalprojectspring.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalsDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserPrincipalsDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findByEmail(email);
        UserPrincipal userPrincipal = new UserPrincipal(userEntity);
        return userPrincipal;
    }
}
