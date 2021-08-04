package com.example.finalprojectspring.dto;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.sun.istack.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntityDTO {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private Set<Role_Of_Users> roles;

}
