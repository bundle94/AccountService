package com.blueharvest.accountservice.Exceptions;

public class AccountNotFoundException extends RuntimeException {
    private String code;

    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
