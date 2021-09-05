package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.BaseResponse;

public interface RequestManager {
    BaseResponse createTransaction(long accountId, double amount);
}
