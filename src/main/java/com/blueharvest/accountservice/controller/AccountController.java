package com.blueharvest.accountservice.controller;

import com.blueharvest.accountservice.model.CreateAccount;
import com.blueharvest.accountservice.model.BaseResponse;
import com.blueharvest.accountservice.model.UserDetails;
import com.blueharvest.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@RestController
@RequestMapping(value = "api/v1/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public Callable<ResponseEntity<BaseResponse>> createAccount(@RequestBody @Valid CreateAccount request) {
        return () -> {
            BaseResponse response = accountService.createAccount(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        };
    }

    @GetMapping("/fetch/{customerId}")
    public Callable<ResponseEntity<UserDetails>> fetchUserDetails(@PathVariable("customerId") long id) {
        return () -> {
            UserDetails response = accountService.fetchUserDetails(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        };
    }
}
