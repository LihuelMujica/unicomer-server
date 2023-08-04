package com.techforb.unicomer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity(name = "USERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dniType")
    private DniType dniType;
    private String dni;

    private String name;

    private String lastname;

    @Column(length = 2048, unique = true)
    @Email
    private String email;

    @Column(length = 60)
    private String password;


    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;


}
