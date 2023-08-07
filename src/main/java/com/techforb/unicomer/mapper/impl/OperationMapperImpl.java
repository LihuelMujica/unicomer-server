package com.techforb.unicomer.mapper.impl;

import com.techforb.unicomer.dto.OperationDTO;
import com.techforb.unicomer.mapper.OperationMapper;
import com.techforb.unicomer.mapper.UserMapper;
import com.techforb.unicomer.model.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationMapperImpl implements OperationMapper {
    private final UserMapper userMapper;

    @Autowired
    public OperationMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public OperationDTO operationToOperationDTO(Operation operation) {
        return OperationDTO.builder()
                .id(operation.getId())
                .operationType(operation.getOperationType())
                .receiverId(operation.getReceiverId())
                .receiver(userMapper.userToPublicUserDTO(operation.getReceiver()))
                .senderId(operation.getSenderId())
                .sender(userMapper.userToPublicUserDTO(operation.getSender()))
                .amount(operation.getAmount())
                .operationState(operation.getOperationState())
                .date(operation.getDate())
                .time(operation.getTime())
                .build();
    }
}
