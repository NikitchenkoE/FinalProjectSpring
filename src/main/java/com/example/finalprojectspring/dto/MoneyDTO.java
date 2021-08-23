package com.example.finalprojectspring.dto;

import com.example.finalprojectspring.entities.RoleOfUsers;
import lombok.*;

import javax.validation.constraints.DecimalMin;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MoneyDTO {

    private String email;

    private String firstName;

    private String lastName;

    private Set<RoleOfUsers> roles;

    private String occupation;

    private double averageRating;

    @DecimalMin(value = "0.0", message = "Your valu must be greater than 0")
    private double money;

}
