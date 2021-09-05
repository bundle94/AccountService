package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.CreateAccount;
import com.blueharvest.accountservice.model.BaseResponse;

public interface AccountService {
    BaseResponse createAccount(CreateAccount request) throws Exception;
}
