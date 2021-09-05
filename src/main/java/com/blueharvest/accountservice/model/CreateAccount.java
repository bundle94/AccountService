package com.blueharvest.accountservice.model;

import javax.validation.constraints.NotBlank;

public class CreateAccount {

    @NotBlank(message = "customer is required")
    private Long customerId;

    @NotBlank(message = "Email is required")
    private double initialCredit;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getInitialCredit() {
        return initialCredit;
    }

    public void setInitialCredit(double initialCredit) {
        this.initialCredit = initialCredit;
    }
}
