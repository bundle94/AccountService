package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.TransactionData;
import com.blueharvest.accountservice.model.TransactionServiceResponse;

public interface RequestManager {
    TransactionServiceResponse createTransaction(long accountId, double amount);
    TransactionServiceResponse fetchTransaction(long accountId);
}
