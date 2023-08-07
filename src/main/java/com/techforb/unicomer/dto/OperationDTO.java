package com.techforb.unicomer.dto;

import com.techforb.unicomer.model.OperationState;
import com.techforb.unicomer.model.OperationType;
import com.techforb.unicomer.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OperationDTO {
    private Integer id;
    private OperationType operationType;
    private Integer receiverId;
    private PublicUserDTO receiver;
    private Integer senderId;
    private PublicUserDTO sender;
    private BigDecimal amount;
    private OperationState operationState;
    private LocalDate date;
    private LocalTime time;
}
