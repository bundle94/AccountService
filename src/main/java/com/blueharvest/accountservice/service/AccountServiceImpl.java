package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.Account;
import com.blueharvest.accountservice.model.CreateAccount;
import com.blueharvest.accountservice.model.BaseResponse;
import com.blueharvest.accountservice.model.Customer;
import com.blueharvest.accountservice.repository.AccountRepository;
import com.blueharvest.accountservice.repository.CustomerRepository;
import com.blueharvest.accountservice.util.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl (CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public BaseResponse createAccount(CreateAccount request) throws Exception {
        //check if user exist using the customer's ID;
        Customer res = customerRepository.findById(request.getCustomerId()).orElse(null);
        if (res == null) {
            throw new Exception(ResponseCodes.CUSTOMER_NOT_FOUND.getMessage());
        }
        //create account
        Account acc = new Account(request.getCustomerId(), request.getInitialCredit());
        accountRepository.save(acc);
        if (request.getInitialCredit() > 0) {
            // Todo: Do implementation for posting transactions
        }

        return null;

    }
}
