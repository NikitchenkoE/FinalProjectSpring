package com.example.finalprojectspring.dto;

import com.example.finalprojectspring.entities.RoleOfUsers;
import com.example.finalprojectspring.validation.ValidPassword;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    private Set<RoleOfUsers> roles;

    @NotNull
    private String occupation;

    private double averageRating;

    @DecimalMin(value = "0.0", message = "Your value must be greater than 0")
    private double money;
}
