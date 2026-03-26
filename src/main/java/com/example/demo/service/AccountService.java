package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.UserRepository;


import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import com.example.demo.model.Account;
import com.example.demo.dto.*;

@Service
public class AccountService {

   private final AccountRepository accountRepository;
   private final UserRepository userRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
         this.accountRepository = accountRepository;
         this.userRepository = userRepository;
    }

    private boolean accountExists(Long accountId) {
        return accountRepository.existsById(accountId);
    }
    public AccountResponse createAccount(CreateAccountRequest request){

        if (accountExists(request.accountNumber())) throw new RuntimeException("Account with this number already exists");
        Account account = new Account();
        account.setId(request.accountNumber());
        account.setBalance(request.initialDeposit());
        account.setAccountType(request.accountType());
        account.setUser(userRepository.findById(request.userId()).orElseThrow(() -> new RuntimeException("User not found")));
        Account savedAccount = accountRepository.save(account);

        return new AccountResponse(savedAccount.getId(), savedAccount.getAccountNumber(), savedAccount.getAccountType(), savedAccount.getUser().getId(), savedAccount.getBalance());
    }

    @Transactional
    public boolean withdraw(Long accountId, BigDecimal amount){


        if (amount.compareTo(BigDecimal.ZERO) <= 0) 
            throw new RuntimeException("Withdrawal amount must be positive");

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if(account.getBalance().compareTo(amount) < 0) 
            throw new RuntimeException("Insufficient funds");
    
      account.setBalance(account.getBalance().subtract(amount));
      accountRepository.save(account);
      return true;

    }
    @Transactional
    public boolean deposit(Long accountId, BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) 
            throw new RuntimeException("Deposit amount must be positive");

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
        return true;
    }

    @Transactional
    public boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount){
        if (amount.compareTo(BigDecimal.ZERO) <= 0) 
            throw new RuntimeException("Transfer amount must be positive");

        if(fromAccountId.equals(toAccountId)) 
            throw new RuntimeException("Cannot transfer to the same account");
        
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new RuntimeException("From account not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new RuntimeException("To account not found"));

        if(fromAccount.getBalance().compareTo(amount) < 0) 
            throw new RuntimeException("Insufficient funds in from account");

    
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        return true;

    }
}