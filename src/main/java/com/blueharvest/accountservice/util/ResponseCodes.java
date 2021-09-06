package com.blueharvest.accountservice.util;

public enum ResponseCodes {
    SUCCESS("S01", "Successful"),
    CUSTOMER_NOT_FOUND("E01", "Customer not found"),
    USER_ACCOUNT_NOT_FOUND("E02", "User account not found"),
    ZERO_INITIAL_CREDIT("S02", "Account created, with a Zero Initial credit");


    private String message;
    private String code;

    ResponseCodes(String code, String message){
        this.code = code;
        this.message = message;
    }
    public String getCode() { return this.code; }
    public String getMessage(){
        return this.message;
    }
}
