package com.example.finalprojectspring.dto;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserEntityDTO {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;

    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    private Set<Role_Of_Users> roles;

}
