package com.techforb.unicomer.dto;

import com.techforb.unicomer.model.DniType;
import jakarta.validation.constraints.Email;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserCreateDTO {
// This is a DTO for the User entity creation
// Contains only the fields needed for the creation of a new user
    private DniType dniType;
    private String dni;
    private String name;
    private String lastname;
    @Email
    private String email;

    private String password;
}
