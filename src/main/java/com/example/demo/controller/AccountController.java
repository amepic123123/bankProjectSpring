package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AccountService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import com.example.demo.dto.CreateAccountRequest;


@RestController
@Validated
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody CreateAccountRequest request){

            return ResponseEntity.ok(accountService.createAccount(request));

    }
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId,@Valid @Positive @RequestParam BigDecimal amount){
   
           accountService.withdraw(accountId, amount);
           return ResponseEntity.ok("Withdraw succesful");

   
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long accountId,@Valid @Positive @RequestParam BigDecimal amount){
    
            accountService.deposit(accountId, amount);
            return ResponseEntity.ok("deposit Succesful");
   
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<?> transfer(@PathVariable Long accountId,@Valid @Positive @RequestParam Long toAccountId,@Positive @Valid @RequestParam BigDecimal amount){
   
            accountService.transfer(accountId, toAccountId, amount);
            return ResponseEntity.ok("Transfer completed");

    }
    
}
