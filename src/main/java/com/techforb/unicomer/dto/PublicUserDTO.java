package com.techforb.unicomer.dto;

import com.techforb.unicomer.model.DniType;
import com.techforb.unicomer.model.Role;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PublicUserDTO {
// This is a DTO for the User entity
// It does not contain sensitive data
    private Integer id;
    private String name;
}
