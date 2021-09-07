package com.blueharvest.accountservice.model;


import javax.validation.constraints.PositiveOrZero;

public class CreateAccount {

    private long customerId;
    @PositiveOrZero
    private double initialCredit;

    public CreateAccount(long customerId, double initialCredit){
        this.customerId = customerId;
        this.initialCredit = initialCredit;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }
}
