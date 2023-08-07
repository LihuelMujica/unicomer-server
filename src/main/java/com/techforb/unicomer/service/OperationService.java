package com.techforb.unicomer.service;

import com.techforb.unicomer.Exception.InsufficientFundsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.OperationDTO;
import com.techforb.unicomer.model.Operation;

import java.math.BigDecimal;
import java.util.List;

public interface OperationService {
    OperationDTO deposit(Integer userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException;
    OperationDTO withdraw(Integer userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException;
    OperationDTO transfer(Integer receiverId, Integer senderId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException;
    BigDecimal getLast30DaysIncome(Integer userId);
    BigDecimal getLast30DaysOutcome(Integer userId);
    List<OperationDTO> getAllTransactionsByUserId(Integer userId);
}
