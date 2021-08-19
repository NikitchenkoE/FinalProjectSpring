package com.example.finalprojectspring.dto;

import com.example.finalprojectspring.entities.Role_Of_Users;
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
public class UserEntityDtoMoney {

    private String email;

    private String firstName;

    private String lastName;

    private Set<Role_Of_Users> roles;

    private String occupation;

    private double averageRating;

    @DecimalMin(value = "0.0", message = "Your valu must be greater than 0")
    private double money;

}
