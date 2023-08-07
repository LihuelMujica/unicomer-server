package com.techforb.unicomer.controller;

import com.techforb.unicomer.Exception.InsufficientFundsException;
import com.techforb.unicomer.Exception.ResourceAlreadyExistsException;
import com.techforb.unicomer.Exception.ResourceNotFoundException;
import com.techforb.unicomer.dto.BalanceHistoryDTO;
import com.techforb.unicomer.dto.OperationDTO;
import com.techforb.unicomer.dto.UserCreateDTO;
import com.techforb.unicomer.dto.UserDTO;
import com.techforb.unicomer.model.Operation;
import com.techforb.unicomer.service.BalanceService;
import com.techforb.unicomer.service.OperationService;
import com.techforb.unicomer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins="*")
public class UserController {
    private final UserService userService;
    private final OperationService operationService;
    private final BalanceService balanceService;

    @Autowired
    public UserController(UserService userService, OperationService operationService, BalanceService balanceService) {
        this.userService = userService;
        this.operationService = operationService;
        this.balanceService = balanceService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserCreateDTO user) throws ResourceAlreadyExistsException, ResourceNotFoundException {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserDTO> getProfile() {
        return new ResponseEntity<>(getUser(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Integer id) throws ResourceNotFoundException {
        UserDTO user = userService.getUserById(id);
        user.setLast30DaysIncome(operationService.getLast30DaysIncome(user.getId()));
        user.setLast30DaysOutcome(operationService.getLast30DaysOutcome(user.getId()));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<BalanceHistoryDTO> getBalanceHistory(@PathVariable Integer id) throws ResourceNotFoundException {
        return new ResponseEntity<>(balanceService.getBalanceHistory(id), HttpStatus.OK);
    }

    @GetMapping("/myBalance")
    public ResponseEntity<BalanceHistoryDTO> getBalanceHistory() throws ResourceNotFoundException {
        UserDTO user = getUser();
        return new ResponseEntity<>(balanceService.getBalanceHistory(user.getId()), HttpStatus.OK);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<OperationDTO>> getAllTransactionsByUserId(@PathVariable Integer id) throws ResourceNotFoundException {
        return new ResponseEntity<>(operationService.getAllTransactionsByUserId(id), HttpStatus.OK);
    }


    @GetMapping("/myTransactions")
    public ResponseEntity<List<OperationDTO>> getAllTransactionsByUser() throws ResourceNotFoundException {
        UserDTO user = getUser();
        List<OperationDTO> operations = operationService.getAllTransactionsByUserId(user.getId());
        //getting the first 5 transactions
        if(operations.size() > 5) {
            operations = operations.subList(0, 5);
        }
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @PostMapping("/{id}/deposit/{amount}")
    public ResponseEntity<OperationDTO> deposit(@PathVariable Integer id, @PathVariable BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        return new ResponseEntity<>(operationService.deposit(id, amount), HttpStatus.OK);
    }

    @PostMapping("/{id}/withdraw/{amount}")
    public ResponseEntity<OperationDTO> withdraw(@PathVariable Integer id, @PathVariable BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        return new ResponseEntity<>(operationService.withdraw(id, amount), HttpStatus.OK);
    }

    private UserDTO getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO user = userService.getUserByEmail(authentication.getName());
        user.setLast30DaysIncome(operationService.getLast30DaysIncome(user.getId()));
        user.setLast30DaysOutcome(operationService.getLast30DaysOutcome(user.getId()));
        return user;
    }
}
