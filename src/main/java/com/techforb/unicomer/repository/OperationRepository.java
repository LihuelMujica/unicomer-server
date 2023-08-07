package com.techforb.unicomer.repository;

import com.techforb.unicomer.model.Operation;
import com.techforb.unicomer.model.OperationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Integer> {


    List<Operation> getAllBySenderIdOrReceiverIdAndDateBetween(Integer senderId, Integer receiverId, LocalDate startDate, LocalDate endDate);
    List<Operation> getAllBySenderIdOrReceiverIdOrderByDateDescTimeDesc(Integer senderId, Integer receiverId);
}
