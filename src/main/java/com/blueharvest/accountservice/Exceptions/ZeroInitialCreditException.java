package com.blueharvest.accountservice.Exceptions;

public class ZeroInitialCreditException extends RuntimeException{

    private String code;

    public ZeroInitialCreditException(String message) {
        super(message);
    }

    public ZeroInitialCreditException(String code, String message) {
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
