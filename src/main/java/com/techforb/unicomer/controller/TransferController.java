package com.techforb.unicomer.controller;

import com.techforb.unicomer.Exception.InsufficientFundsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.OperationDTO;
import com.techforb.unicomer.dto.TransferDTO;
import com.techforb.unicomer.model.Operation;
import com.techforb.unicomer.service.OperationService;
import com.techforb.unicomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@CrossOrigin(origins="*")
public class TransferController {
    private final UserService userService;
    private final OperationService operationService;

    @Autowired
    public TransferController(UserService userService, OperationService operationService) {
        this.userService = userService;
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<OperationDTO> transfer(@RequestBody TransferDTO transfer) throws InsufficientFundsException, ResourceNotFoundException {
        return new ResponseEntity<>(operationService.transfer(transfer.getReceiverId(), transfer.getSenderId(), transfer.getAmount()), HttpStatus.OK);    }
}
