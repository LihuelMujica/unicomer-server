package com.techforb.unicomer.mapper.impl;

import com.techforb.unicomer.dto.CardDTO;
import com.techforb.unicomer.mapper.CardMapper;
import com.techforb.unicomer.model.Card;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CardMapperImpl implements CardMapper {
    @Override
    public CardDTO cardToCardDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setCardHolderName(card.getCardHolderName());
        cardDTO.setLast4Digits(card.getCardNumber().substring(card.getCardNumber().length() - 4));
        cardDTO.setCardExpirationDate(card.getCardExpirationDate().format(DateTimeFormatter.ofPattern("MM/yy")));
        return cardDTO;
    }
}
