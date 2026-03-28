package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.AccountService;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import org.springframework.http.ResponseEntity;
import com.example.demo.dto.CreateAccountRequest;


@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest request){
        try{
            return ResponseEntity.ok(accountService.createAccount(request));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<?> withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount, @RequestParam Long loggedInUserId){
        try{
           accountService.withdraw(accountId, amount, loggedInUserId);

           return ResponseEntity.ok("Withdraw succesful");

        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<?> deposit(@PathVariable Long accountId,@Valid @RequestParam BigDecimal amount){
        try{
            accountService.deposit(accountId, amount);
            return ResponseEntity.ok("deposit Succesful");
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<?> transfer(@Valid @PathVariable Long accountId, @Valid @RequestParam Long toAccountId, @Valid @RequestParam BigDecimal amount){
        try{
            accountService.transfer(accountId, toAccountId, amount);
            return ResponseEntity.ok("Transfer completed");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
}
