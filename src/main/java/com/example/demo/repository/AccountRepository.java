package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.Account;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findById(Long id);
    boolean existsByIdAndUserId(Long accountId, Long userId);
    long countByUserId(Long userId);
}
