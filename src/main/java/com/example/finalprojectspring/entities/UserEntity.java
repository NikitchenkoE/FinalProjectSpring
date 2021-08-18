package com.example.finalprojectspring.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "usr",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long ID;

    @Column(nullable = false, unique = true, length = 45)
    private String email;

    @Column(nullable = false, length = 64)
    private String password;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "user_role")
    @ElementCollection(targetClass = Role_Of_Users.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role_Of_Users> roles;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "occupation_id")
    private MasterOcupationEntity occupation;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    List<ScheduleEntity> schedule;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Rating> ratings;

}
