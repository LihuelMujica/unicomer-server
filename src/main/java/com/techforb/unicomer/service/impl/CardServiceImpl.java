package com.techforb.unicomer.service.impl;

import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.CardDTO;
import com.techforb.unicomer.mapper.CardMapper;
import com.techforb.unicomer.model.Card;
import com.techforb.unicomer.model.User;
import com.techforb.unicomer.repository.CardRepository;
import com.techforb.unicomer.repository.UserRepository;
import com.techforb.unicomer.service.CardService;
import com.techforb.unicomer.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final CardMapper cardMapper;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, UserRepository userRepository, CardMapper cardMapper) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.cardMapper = cardMapper;
    }

    @Override
    public CardDTO getCardByUserId(Integer userId) throws ResourceNotFoundException {
        System.out.println("userId: " + userId);
        return cardRepository.findByUserId(userId).map(cardMapper::cardToCardDTO).orElseThrow(() -> new ResourceNotFoundException("Card not found"));
    }

    @Override
    public Card createCard(Integer userId) throws ResourceNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Card card = new Card();
        card.setUserId(userId);
        card.setCardHolderName(user.getName() + " " + user.getLastname());
        // set random card number of 16 digits
        card.setCardNumber(Util.generateCreditCardNumber());
        card.setCardCvv(Util.generateCVV());
        // set expiration date of 5 years from now
        card.setCardExpirationDate(LocalDate.now().plusYears(5));
        Card cardSaved = cardRepository.save(card);
        System.out.println("cardSaved: " + cardSaved);
        return cardSaved;
    }
}
