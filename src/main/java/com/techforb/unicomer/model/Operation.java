package com.techforb.unicomer.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "operations")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private OperationType operationType;
    @Column(name = "receiver_id")
    private Integer receiverId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", insertable = false, updatable = false)
    private User receiver;
    @Column(name = "sender_id")
    private Integer senderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", insertable = false, updatable = false)
    private User sender;
    private BigDecimal amount;
//    @Enumerated(EnumType.STRING)
    private OperationState operationState;
    private LocalDate date;
    private LocalTime time;
}
