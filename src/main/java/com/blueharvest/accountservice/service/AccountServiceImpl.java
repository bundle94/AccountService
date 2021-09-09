package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.Exceptions.AccountNotFoundException;
import com.blueharvest.accountservice.Exceptions.CustomerNotFoundException;
import com.blueharvest.accountservice.Exceptions.ZeroInitialCreditException;
import com.blueharvest.accountservice.model.*;
import com.blueharvest.accountservice.repository.AccountRepository;
import com.blueharvest.accountservice.repository.CustomerRepository;
import com.blueharvest.accountservice.util.ResponseCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
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
    public BaseResponse createAccount(CreateAccount request) {
        //check if user exist using the customer's ID;
        Customer res = customerRepository.findById(request.getCustomerId()).orElse(null);
        if (res == null) {
            throw new CustomerNotFoundException(ResponseCodes.CUSTOMER_NOT_FOUND.getMessage());
        }
        //create account
        Account account = new Account(request.getCustomerId(), 0.00, new Date());
        accountRepository.save(account);
        if (request.getInitialCredit() > 0) {
            return createTransactionAndUpdateBalance(account.getId(), request.getInitialCredit());
        }
        throw new ZeroInitialCreditException(ResponseCodes.ZERO_INITIAL_CREDIT.getMessage());

    }

    @Override
    public UserDetails fetchUserDetails(long id) {
        UserDetails response = new UserDetails();
        response.setAccountDetails(new ArrayList<>());
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null)
            throw new CustomerNotFoundException(ResponseCodes.CUSTOMER_NOT_FOUND.getMessage());
        response.setName(customer.getName());
        response.setSurname(customer.getSurname());
        List<Account> account = accountRepository.findAllByCustomerId(customer.getId()).orElse(null);
        if(account == null)
            throw new AccountNotFoundException(ResponseCodes.USER_ACCOUNT_NOT_FOUND.getMessage());
        //stream through user accounts
        account.parallelStream().forEach(e -> {
            AccountDetail accountDetail = new AccountDetail();
            accountDetail.setBalance(e.getBalance());
            accountDetail.setAccountId(e.getId());
            accountDetail.setTransaction(new ArrayList<>());
            LOGGER.info("Account ID to use is :" + e.getId());
            //Make http call to transaction service to get transaction for each account
            TransactionServiceResponse res = requestManager.fetchTransaction(e.getId());
            res.getData().parallelStream().forEach(trans -> {
                TransactionData data = new TransactionData();
                data.setId(trans.getId());
                data.setAccountId(trans.getAccountId());
                data.setAmount(trans.getAmount());
                data.setTransactionDate(trans.getTransactionDate());
                accountDetail.getTransaction().add(data);
            });
            response.getAccountDetails().add(accountDetail);
        });
        return response;
    }

    private BaseResponse createTransactionAndUpdateBalance(long accountId, double amount) {
        BaseResponse response = new BaseResponse();
        Account account = accountRepository.findById(accountId).orElse(null);
        if(account != null) {
            // Make request to transaction service
            LOGGER.info("About to make an API request call to transaction service");
            TransactionServiceResponse res = requestManager.createTransaction(accountId, amount);
            if(res.getError() == null && ResponseCodes.SUCCESS.getCode().equals(res.getResponseCode())) {
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
