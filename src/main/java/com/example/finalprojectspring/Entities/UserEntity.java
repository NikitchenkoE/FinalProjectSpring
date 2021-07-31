package com.example.finalprojectspring.Entities;

import javax.persistence.*;

@Entity
@Table(name = "user",
        uniqueConstraints = {@UniqueConstraint(columnNames={"email"}),
                @UniqueConstraint(columnNames={"phone_number"})})
public class UserEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long ID;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "phone_number", nullable = false)
    private String phone;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role_Of_Users role;

}
