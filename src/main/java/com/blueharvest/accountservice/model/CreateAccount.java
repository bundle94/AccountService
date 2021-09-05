package com.blueharvest.accountservice.model;

import javax.validation.constraints.NotBlank;

public class CreateAccount {

    //@NotBlank(message = "customer is required")
    private long customerId;

    private double initialCredit;

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
