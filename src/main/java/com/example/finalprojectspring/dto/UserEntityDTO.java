package com.example.finalprojectspring.dto;

import com.example.finalprojectspring.entities.Role_Of_Users;
import com.example.finalprojectspring.validation.ValidPassword;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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

    @ValidPassword
    private String password;

    @NotNull
    @NotBlank(message = "Name should not be empty")
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @NotBlank(message = "Surname should not be empty")
    @Size(min = 2, max = 30)
    private String lastName;

    private Set<Role_Of_Users> roles;

    @NotNull
    private String occupation;
}
