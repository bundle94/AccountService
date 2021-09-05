package com.blueharvest.accountservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;

    @PositiveOrZero
    private double initialCredit;

    private long balance;

    public Account() {
    }

    public Account(Long customerId, double initialCredit) {
        this.customerId = customerId;
        this.initialCredit = initialCredit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public void addBalance(long transactionAmount) {
        balance += transactionAmount;
    }
}
