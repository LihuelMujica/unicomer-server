package com.techforb.unicomer.controller;

import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.CardDTO;
import com.techforb.unicomer.dto.UserDTO;
import com.techforb.unicomer.service.CardService;
import com.techforb.unicomer.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@CrossOrigin(origins="*")
public class CardController {
    private final CardService cardService;
    private final UserService userService;

    public CardController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @GetMapping("/myCard")
    public ResponseEntity<CardDTO> getCard() throws ResourceNotFoundException {
        return new ResponseEntity<>(cardService.getCardByUserId(getUser().getId()), HttpStatus.OK);
    }

    @GetMapping("/generate")
    public ResponseEntity<?> generateCard() throws ResourceNotFoundException {
        cardService.createCard(getUser().getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    private UserDTO getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUserByEmail(authentication.getName());
    }
}
