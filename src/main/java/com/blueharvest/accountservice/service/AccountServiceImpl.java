package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.Exceptions.AccountNotFoundException;
import com.blueharvest.accountservice.Exceptions.CustomerNotFoundException;
import com.blueharvest.accountservice.Exceptions.ZeroInitialCreditException;
import com.blueharvest.accountservice.model.*;
import com.blueharvest.accountservice.repository.AccountRepository;
import com.blueharvest.accountservice.repository.CustomerRepository;
import com.blueharvest.accountservice.util.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService {

    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private RestTemplate restTemplate;
    private RequestManager requestManager;

    @Autowired
    public AccountServiceImpl (CustomerRepository customerRepository, AccountRepository accountRepository, RequestManager requestManager) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.restTemplate = new RestTemplate();
        this.requestManager = requestManager;
    }

    @Override
    public BaseResponse createAccount(CreateAccount request) throws Exception {
        BaseResponse response;
        //check if user exist using the customer's ID;
        Customer res = customerRepository.findById(request.getCustomerId()).orElse(null);
        if (res == null) {
            throw new CustomerNotFoundException(ResponseCodes.CUSTOMER_NOT_FOUND.getMessage());
        }
        //create account
        Account account = new Account(request.getCustomerId(), request.getInitialCredit(), new Date());
        accountRepository.save(account);
        if (request.getInitialCredit() > 0) {
            return response = createTransactionAndUpdateBalance(account.getId(), request.getInitialCredit());
        }
        throw new ZeroInitialCreditException(ResponseCodes.ZERO_INITIAL_CREDIT.getMessage());

    }

    private BaseResponse createTransactionAndUpdateBalance(long accountId, double amount) {
        BaseResponse response;
        Account account = accountRepository.findById(accountId).orElse(null);
        if(account != null) {
            response = requestManager.createTransaction(accountId, amount);
            if(!response.getResponseCode().equals(ResponseCodes.SUCCESS.getCode())) {
                double currentBalance = account.getBalance();
                currentBalance += amount;
                account.setBalance(currentBalance);
                accountRepository.save(account);
                response.setResponseCode(ResponseCodes.SUCCESS.getCode());
                response.setResponseMessage(ResponseCodes.SUCCESS.getMessage());
            }
            else {
                response.setResponseCode(response.getResponseCode());
                response.setResponseMessage(response.getResponseMessage());
            }
           return response;
        }
        throw new AccountNotFoundException(ResponseCodes.USER_ACCOUNT_NOT_FOUND.getMessage());
    }
}
