package com.techforb.unicomer.controller;

import com.techforb.unicomer.Exception.ResourceAlreadyExistsException;
import com.techforb.unicomer.dto.UserCreateDTO;
import com.techforb.unicomer.dto.UserDTO;
import com.techforb.unicomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO user) throws ResourceAlreadyExistsException {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
