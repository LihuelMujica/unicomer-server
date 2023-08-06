package com.techforb.unicomer.repository;

import com.techforb.unicomer.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Optional<Card> findByUserId(Integer userId);
}
