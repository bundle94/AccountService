package com.blueharvest.accountservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDetail {
    private long accountId;
    private double balance;
    private List<TransactionData> transaction;


    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<TransactionData> getTransaction() {
        return transaction;
    }

    public void setTransaction(List<TransactionData> transaction) {
        this.transaction = transaction;
    }


}
