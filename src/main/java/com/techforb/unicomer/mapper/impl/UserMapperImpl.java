package com.techforb.unicomer.mapper.impl;

import com.techforb.unicomer.dto.PublicUserDTO;
import com.techforb.unicomer.dto.UserCreateDTO;
import com.techforb.unicomer.dto.UserDTO;
import com.techforb.unicomer.mapper.UserMapper;
import com.techforb.unicomer.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setDniType(user.getDniType());
        userDTO.setDni(user.getDni());
        userDTO.setName(user.getName());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        userDTO.setBalance(user.getBalance());
        return userDTO;
    }

    @Override
    public User userDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setDniType(userDTO.getDniType());
        user.setDni(userDTO.getDni());
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }

    @Override
    public User userCreateDTOToUser(UserCreateDTO userDTO) {
        User user = new User();
        user.setDniType(userDTO.getDniType());
        user.setDni(userDTO.getDni());
        user.setName(userDTO.getName());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    @Override
    public PublicUserDTO userToPublicUserDTO(User user) {
        PublicUserDTO publicUserDTO = new PublicUserDTO();
        publicUserDTO.setId(user.getId());
        publicUserDTO.setName(user.getName() + " " + user.getLastname());
        return publicUserDTO;
    }
}
