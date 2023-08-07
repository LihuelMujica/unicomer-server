package com.techforb.unicomer.repository;

import com.techforb.unicomer.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    Optional<Balance> findByDateAndUserId(LocalDate date, Integer userId);
    List<Balance> findAllByUserIdAndDateBetween(Integer userId, LocalDate start, LocalDate end);
}
