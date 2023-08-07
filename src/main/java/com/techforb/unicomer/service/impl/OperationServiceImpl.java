package com.techforb.unicomer.service.impl;

import com.techforb.unicomer.Exception.InsufficientFundsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.OperationDTO;
import com.techforb.unicomer.mapper.OperationMapper;
import com.techforb.unicomer.model.Operation;
import com.techforb.unicomer.model.OperationState;
import com.techforb.unicomer.model.OperationType;
import com.techforb.unicomer.repository.OperationRepository;
import com.techforb.unicomer.repository.UserRepository;
import com.techforb.unicomer.service.BalanceService;
import com.techforb.unicomer.service.OperationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final UserRepository userRepository;
    private final BalanceService balanceService;
    private final OperationMapper operationMapper;

    public OperationServiceImpl(OperationRepository operationRepository, UserRepository userRepository, BalanceService balanceService, OperationMapper operationMapper) {
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
        this.balanceService = balanceService;
        this.operationMapper = operationMapper;
    }

    @Override
    public OperationDTO deposit(Integer userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        Operation operation = createOperation(userId, userId, amount, OperationType.DEPOSIT, OperationState.SUCCESS);
        balanceService.addBalance(userId, amount);
        return operationMapper.operationToOperationDTO(operationRepository.save(operation));

    }

    @Override
    public OperationDTO withdraw(Integer userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        Operation operation = createOperation(userId, userId, amount, OperationType.WITHDRAW, OperationState.SUCCESS);
        balanceService.subtractBalance(userId, amount);
        return operationMapper.operationToOperationDTO(operationRepository.save(operation));
    }
    private Operation createOperation(Integer senderId, Integer receiverId, BigDecimal amount, OperationType operationType, OperationState operationState) {
        Operation operation = new Operation();
        operation.setAmount(amount);
        operation.setReceiverId(receiverId);
        operation.setSenderId(senderId);
        operation.setOperationType(operationType);
        operation.setOperationState(operationState);
        operation.setDate(LocalDate.now());
        operation.setTime(LocalTime.now());
        return operation;
    }


    @Override
    public OperationDTO transfer(Integer receiverId, Integer senderId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        Operation operation = createOperation(senderId, receiverId, amount, OperationType.TRANSFER, OperationState.SUCCESS);
        try {
            balanceService.subtractBalance(senderId, amount);
        } catch (InsufficientFundsException e) {
            operation.setOperationState(OperationState.FAILED);
            operationRepository.save(operation);
            throw new InsufficientFundsException("Insufficient funds");
        }
        balanceService.addBalance(receiverId, amount);
        Operation operationSaved = operationRepository.save(operation);
        return operationMapper.operationToOperationDTO(operationRepository.findById(operationSaved.getId()).orElseThrow(() -> new ResourceNotFoundException("Operation not found")));
    }

    @Override
    public BigDecimal getLast30DaysIncome(Integer userId) {
        System.out.println("Hola");
        List<Operation> operations = operationRepository.getAllBySenderIdOrReceiverIdAndDateBetween(userId, userId, LocalDate.now().minusDays(30), LocalDate.now());
        System.out.println("operations: " + operations);
        return operations.stream()
                .filter(operation -> operation.getOperationState().equals(OperationState.SUCCESS))
                .filter(operation -> !operation.getOperationType().equals(OperationType.WITHDRAW))
                .filter(operation -> operation.getReceiverId().equals(userId))
                .map(Operation::getAmount)
                .reduce(BigDecimal::add).orElse(new BigDecimal(0));
    }

    @Override
    public BigDecimal getLast30DaysOutcome(Integer userId) {
        List<Operation> operations = operationRepository.getAllBySenderIdOrReceiverIdAndDateBetween(userId, userId, LocalDate.now().minusDays(30), LocalDate.now());
        return operations.stream()
                .filter(operation -> operation.getOperationState().equals(OperationState.SUCCESS))
                .filter(operation -> !operation.getOperationType().equals(OperationType.DEPOSIT))
                .filter(operation -> operation.getSenderId().equals(userId))
                .map(Operation::getAmount)
                .reduce(BigDecimal::add).orElse(new BigDecimal(0));
    }

    @Override
    public List<OperationDTO> getAllTransactionsByUserId(Integer userId) {
        List<Operation> operations = operationRepository.getAllBySenderIdOrReceiverIdOrderByDateDescTimeDesc(userId, userId);
        return operations
                .stream().filter(operation -> operation.getOperationType().equals(OperationType.TRANSFER))
                .map(operationMapper::operationToOperationDTO)
                .toList();
    }
}
