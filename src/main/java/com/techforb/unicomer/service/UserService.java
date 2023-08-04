package com.techforb.unicomer.service;

import com.techforb.unicomer.Exception.ResourceAlreadyExistsException;
import com.techforb.unicomer.dto.UserCreateDTO;
import com.techforb.unicomer.dto.UserDTO;
import com.techforb.unicomer.model.DniType;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDTO createUser(UserCreateDTO userCreateDTO) throws ResourceAlreadyExistsException;
    UserDTO getUserByEmail(String email);

    UserDTO getUserByDniAndDniType(String dni, DniType dniType);
}
