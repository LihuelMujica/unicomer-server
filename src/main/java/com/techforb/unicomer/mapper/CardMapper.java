package com.techforb.unicomer.mapper;

import com.techforb.unicomer.dto.CardDTO;
import com.techforb.unicomer.model.Card;

public interface CardMapper {
    CardDTO cardToCardDTO(Card card);
}
