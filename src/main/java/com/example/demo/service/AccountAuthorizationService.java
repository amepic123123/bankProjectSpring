package com.example.demo.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.example.demo.repository.AccountRepository;


@Component
public class AccountAuthorizationService {

    private final AccountRepository accountRepository;

    public AccountAuthorizationService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

       private Long getLoggedInUserId(){
        
        if(SecurityContextHolder.getContext().getAuthentication() == null) return null;

       Object principal = SecurityContextHolder.getContext()
        .getAuthentication()
        .getPrincipal();
        if (principal instanceof Long) {
            return (Long) principal;
        }
        return null;
    } 

public boolean canAccessAccount(Long accountId) {
    Long userId = getLoggedInUserId();
    if (userId == null) return false;

    return accountRepository.existsByIdAndUserId(accountId, userId);
    }
}
