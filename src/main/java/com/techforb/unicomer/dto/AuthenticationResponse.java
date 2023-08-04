package com.techforb.unicomer.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuthenticationResponse {
    private String jwt;
    private UserDTO user;
}
