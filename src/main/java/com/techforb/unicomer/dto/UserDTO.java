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
public class UserDTO {
// This is a DTO for the User entity class with the same fields except for the password
// contains sensitive data
    private Integer id;
    private DniType dniType;
    private String dni;
    private String name;
    private String lastname;
    @Email
    private String email;
    private Role role;
    private BigDecimal balance;
    private BigDecimal last30DaysIncome;
    private BigDecimal last30DaysOutcome;
}
