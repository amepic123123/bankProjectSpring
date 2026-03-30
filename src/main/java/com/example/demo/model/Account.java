package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Long accountNumber;

    @Column(nullable = false)
    private BigDecimal balance;


    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAccountNumber() { return accountNumber; }
    public void setAccountNumber(Long accountNumber) { this.accountNumber = accountNumber; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user;}

    public AccountType getAccountType() {return accountType;}
    public void setAccountType(AccountType accountType) {this.accountType = accountType;}
    
}
