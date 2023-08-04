package com.techforb.unicomer.dto;

import com.techforb.unicomer.model.DniType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    DniType dniType;
    String dni;
    String password;
}
