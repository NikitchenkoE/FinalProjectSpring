package com.example.finalprojectspring.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MasterOcupation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MasterOcupationEntity extends BaseEntity{


    @OneToOne(mappedBy = "occupation", cascade = CascadeType.ALL)
    private UserEntity userEntity;

    private String ocupation;


}
