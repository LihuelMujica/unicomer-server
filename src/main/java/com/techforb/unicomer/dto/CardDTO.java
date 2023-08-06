package com.techforb.unicomer.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CardDTO {

    private Integer id;
    private String cardHolderName;
    private String last4Digits;
    private String cardExpirationDate;

}
