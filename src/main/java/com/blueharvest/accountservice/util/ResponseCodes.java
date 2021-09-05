package com.blueharvest.accountservice.util;

public enum ResponseCodes {
    SUCCESS("Successful"),
    CUSTOMER_NOT_FOUND("Customer not found");


    private String message;

    ResponseCodes(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
