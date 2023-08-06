package com.techforb.unicomer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity(name = "cards")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Card {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String cardHolderName;
        private String cardNumber;
        private LocalDate cardExpirationDate;
        private String cardCvv;
        private Integer userId;
}
