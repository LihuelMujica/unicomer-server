package com.techforb.unicomer.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransferDTO {
    private Integer senderId;
    private Integer receiverId;
    private BigDecimal amount;
}
