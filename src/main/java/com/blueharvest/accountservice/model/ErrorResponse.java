package com.blueharvest.accountservice.model;

public class ErrorResponse {
    private BaseResponse error;

    public ErrorResponse (BaseResponse error) {
        this.error = error;
    }

    public BaseResponse getError() {
        return error;
    }

    public void setError(BaseResponse error) {
        this.error = error;
    }
}
