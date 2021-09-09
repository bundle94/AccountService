package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.CreateAccount;
import com.blueharvest.accountservice.model.BaseResponse;
import com.blueharvest.accountservice.model.UserDetails;

public interface AccountService {
    BaseResponse createAccount(CreateAccount request);
    UserDetails fetchUserDetails(long id);

}
