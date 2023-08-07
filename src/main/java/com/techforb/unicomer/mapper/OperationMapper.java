package com.techforb.unicomer.mapper;

import com.techforb.unicomer.dto.OperationDTO;
import com.techforb.unicomer.model.Operation;

public interface OperationMapper {
    OperationDTO operationToOperationDTO(Operation operation);
}
