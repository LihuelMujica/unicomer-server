package com.techforb.unicomer.service.impl;

import com.techforb.unicomer.Exception.ResourceAlreadyExistsException;
import com.techforb.unicomer.dto.UserCreateDTO;
import com.techforb.unicomer.dto.UserDTO;
import com.techforb.unicomer.mapper.UserMapper;
import com.techforb.unicomer.model.DniType;
import com.techforb.unicomer.model.Role;
import com.techforb.unicomer.model.User;
import com.techforb.unicomer.repository.UserRepository;
import com.techforb.unicomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) throws ResourceAlreadyExistsException {
         if(
                 userRepository.existsByDniAndDniType(userCreateDTO.getDni(), userCreateDTO.getDniType()) ||
                 userRepository.existsByEmail(userCreateDTO.getEmail()))
         {
             throw new ResourceAlreadyExistsException("User already exists");
         }

        User user = userMapper.userCreateDTOToUser(userCreateDTO);
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setRole(Role.USER);
        return userMapper.userToUserDTO(userRepository.save(user));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDTO getUserByDniAndDniType(String dni, DniType dniType) {
        return userRepository.findByDniAndDniType(dni, dniType)
                .map(userMapper::userToUserDTO)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(username);

        if(user.isPresent()) {
            User userFound = user.get();
                    return org.springframework.security.core.userdetails.User
                            .withUsername(userFound.getEmail())
                            .password(userFound.getPassword())
                            .roles(userFound.getRole().name())
                            .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}
