package com.blueharvest.accountservice.Exceptions;

import com.blueharvest.accountservice.model.BaseResponse;
import com.blueharvest.accountservice.util.ResponseCodes;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvise {

    @ExceptionHandler({CustomerNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public BaseResponse handleCustomerNotFoundException(CustomerNotFoundException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.CUSTOMER_NOT_FOUND.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({AccountNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public BaseResponse handleAccountNotFoundException(AccountNotFoundException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.USER_ACCOUNT_NOT_FOUND.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler({ZeroInitialCreditException.class})
    @ResponseBody
    public BaseResponse handleZeroInitialCreditException(ZeroInitialCreditException e) {
        BaseResponse response = new BaseResponse();
        response.setResponseCode(StringUtils.hasText(e.getCode()) ? e.getCode() : ResponseCodes.ZERO_INITIAL_CREDIT.getCode());
        response.setResponseMessage(e.getMessage());
        return response;
    }
}
