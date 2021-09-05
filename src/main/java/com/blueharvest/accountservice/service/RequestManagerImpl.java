package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.BaseResponse;
import com.blueharvest.accountservice.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestManagerImpl implements RequestManager {

    private RestTemplate restTemplate;

    @Value("${blueharvest.transactionService.baseUrl}")
    private String baseUrl;

    @Value("${blueharvest.transactionService.createEndpoint}")
    private String createEndpoint;

    @Autowired
    public RequestManagerImpl() {
        restTemplate = new RestTemplate();
    }
    @Override
    public BaseResponse createTransaction(long accountId, double amount) {
        String url = baseUrl.concat(createEndpoint);
        HttpEntity<Transaction> entity = new HttpEntity<>(new Transaction(accountId, amount));
        ResponseEntity<BaseResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, BaseResponse.class);
        return responseEntity.getBody();
    }
}
