package com.techforb.unicomer.mapper;

import com.techforb.unicomer.dto.UserCreateDTO;
import com.techforb.unicomer.dto.UserDTO;
import com.techforb.unicomer.model.User;

public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    User userCreateDTOToUser(UserCreateDTO userDTO);
}
