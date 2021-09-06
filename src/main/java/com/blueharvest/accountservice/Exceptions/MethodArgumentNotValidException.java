package com.blueharvest.accountservice.Exceptions;

import javax.validation.ValidationException;

public class MethodArgumentNotValidException extends ValidationException {
    private String code;

    public MethodArgumentNotValidException(String message) {
        super(message);
    }

    public MethodArgumentNotValidException(String code, String message) {
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
