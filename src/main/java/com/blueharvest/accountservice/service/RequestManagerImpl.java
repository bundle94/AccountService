package com.blueharvest.accountservice.service;

import com.blueharvest.accountservice.model.CreateTransaction;
import com.blueharvest.accountservice.model.TransactionData;
import com.blueharvest.accountservice.model.TransactionServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RequestManagerImpl implements RequestManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestManagerImpl.class);
    private RestTemplate restTemplate;

    @Value("${blueharvest.transactionService.baseUrl}")
    private String baseUrl;

    @Value("${blueharvest.transactionService.createEndpoint}")
    private String createEndpoint;

    @Value("${blueharvest.transactionService.fetchEndpoint}")
    private String fetchEndpoint;

    @Autowired
    public RequestManagerImpl() {
        restTemplate = new RestTemplate();
    }
    @Override
    public TransactionServiceResponse createTransaction(long accountId, double amount) {
        String url = baseUrl.concat(createEndpoint);
        HttpEntity<CreateTransaction> entity = new HttpEntity<>(new CreateTransaction(accountId, amount));
        LOGGER.info("Calling transaction service : " + url);
        ResponseEntity<TransactionServiceResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, TransactionServiceResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public TransactionServiceResponse fetchTransaction(long accountId) {
        String url = baseUrl.concat(fetchEndpoint);
        String fullPath = url + "/" + accountId;
        LOGGER.info("Calling transaction service : " + url + "/" + accountId);
        ResponseEntity<TransactionServiceResponse> responseEntity = restTemplate.exchange(fullPath, HttpMethod.GET, null, TransactionServiceResponse.class);
        return responseEntity.getBody();
    }
}
