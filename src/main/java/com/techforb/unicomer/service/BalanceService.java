package com.techforb.unicomer.service;

import com.techforb.unicomer.Exception.InsufficientFundsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.BalanceHistoryDTO;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public interface BalanceService {
    void addBalance(Integer userId, @Positive BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException;
    void subtractBalance(Integer userId, @Positive BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException;

    BalanceHistoryDTO getBalanceHistory(Integer userId) throws ResourceNotFoundException;
}
