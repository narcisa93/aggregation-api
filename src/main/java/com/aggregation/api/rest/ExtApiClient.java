package com.aggregation.api.rest;

import com.aggregation.api.exception.RestApiException;
import com.aggregation.api.properties.RestApiProperties;
import com.aggregation.api.rest.dto.AccountDto;
import com.aggregation.api.rest.dto.Token;
import com.aggregation.api.rest.dto.TransactionDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class ExtApiClient {

    @Autowired
    private RestApiClient restApi;

    @Autowired
    private RestApiProperties properties;

    @Autowired
    private ObjectMapper mapper;

    public List<AccountDto> getAccounts(String token) throws RestApiException {
        String accountsPathUrl = properties.getAccountsPath();
        ResponseEntity<String> responseEntity = restApi.doGetRequest(accountsPathUrl, token, properties);
        List<AccountDto> accounts = null;
        try {
            accounts = mapper.readValue(responseEntity.getBody(), new TypeReference<List<AccountDto>>(){});
        } catch (IOException e) {
            throw new RestApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return accounts;
    }

    public String getJWTToken(String username) throws RestApiException {
        String loginPathUrl = properties.getLoginPath();
        ResponseEntity<String> responseEntity = restApi.doPostRequest(loginPathUrl, username, properties);
        Token token = null;
        try {
            token = mapper.readValue(responseEntity.getBody(), Token.class);
        } catch (IOException e) {
            throw new RestApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return token.getTokenValue();
    }

    public List<TransactionDto> getTransactions(String token) throws RestApiException {
        String transactionsPathUrl = properties.getTransactionsPath();
        ResponseEntity<String> responseEntity = restApi.doGetRequest(transactionsPathUrl, token, properties);
        List<TransactionDto> transactions = null;
        try {
            transactions = mapper.readValue(responseEntity.getBody(),  new TypeReference<List<TransactionDto>>(){});
        } catch (JsonProcessingException e) {
            throw new RestApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return transactions;
    }
}
