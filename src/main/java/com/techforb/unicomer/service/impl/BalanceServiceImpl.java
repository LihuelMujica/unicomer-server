package com.techforb.unicomer.service.impl;

import com.techforb.unicomer.Exception.InsufficientFundsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.BalanceHistoryDTO;
import com.techforb.unicomer.model.Balance;
import com.techforb.unicomer.model.User;
import com.techforb.unicomer.repository.BalanceRepository;
import com.techforb.unicomer.repository.UserRepository;
import com.techforb.unicomer.service.BalanceService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;
    private final UserRepository userRepository;

    @Autowired
    public BalanceServiceImpl(BalanceRepository balanceRepository, UserRepository userRepository) {
        this.balanceRepository = balanceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addBalance(Integer userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        updateBalance(userId, amount);
    }

    @Override
    public void subtractBalance(Integer userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        updateBalance(userId, amount.negate());
    }

    @Override
    public BalanceHistoryDTO getBalanceHistory(Integer userId) throws ResourceNotFoundException {
        if (!userRepository.existsById(userId)) throw new ResourceNotFoundException("User with id " + userId + " not found");
        return BalanceHistoryDTO.builder()
                .thisWeek(balanceRepository.findAllByUserIdAndDateBetween(userId, LocalDate.now().minusDays(7), LocalDate.now()))
                .lastWeek(balanceRepository.findAllByUserIdAndDateBetween(userId, LocalDate.now().minusDays(14), LocalDate.now().minusDays(7)))
                .build();
    }

    private void updateBalance(Integer userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
        if (amount.compareTo(BigDecimal.ZERO) < 0 && user.getBalance().compareTo(amount.negate()) < 0)
            throw new InsufficientFundsException("Insufficient funds");
        Balance balance = balanceRepository.findByDateAndUserId(LocalDate.now(), userId)
                .orElse(Balance.builder().userId(userId).build());

        balance.setBalance(user.getBalance().add(amount));
        balance.setDate(LocalDate.now());
        user.setBalance(user.getBalance().add(amount));
        userRepository.save(user);
        balanceRepository.save(balance);
    }


}
