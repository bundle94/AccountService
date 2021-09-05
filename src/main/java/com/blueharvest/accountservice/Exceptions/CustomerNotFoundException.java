package com.blueharvest.accountservice.Exceptions;

public class CustomerNotFoundException extends RuntimeException {
    private String code;

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String code, String message) {
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
