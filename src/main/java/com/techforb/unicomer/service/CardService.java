package com.techforb.unicomer.service;

import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.CardDTO;
import com.techforb.unicomer.model.Card;

public interface CardService {
    public CardDTO getCardByUserId(Integer userId) throws ResourceNotFoundException;
    public Card createCard(Integer userId) throws ResourceNotFoundException;
}
