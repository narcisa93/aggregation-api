package com.aggregation.api.scheduler;

import com.aggregation.api.converter.DtoConverter;
import com.aggregation.api.exception.RestApiException;
import com.aggregation.api.model.Account;
import com.aggregation.api.model.Transaction;
import com.aggregation.api.model.User;
import com.aggregation.api.rest.ExtApiClient;
import com.aggregation.api.rest.dto.AccountDto;
import com.aggregation.api.rest.dto.TransactionDto;
import com.aggregation.api.service.AccountService;
import com.aggregation.api.service.CustomUserDetailService;
import com.aggregation.api.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RefreshDataTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshDataTask.class);

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    private ExtApiClient extApiClient;

    @Autowired
    private CustomUserDetailService userService;

    public void updateData() {
        Iterable<User> allUsers = userService.findAllUsers();
        allUsers.forEach(u -> {
            try {
                LOGGER.error("Retrieving token for " + u.getUsername());
                String token = extApiClient.getJWTToken(u.getUsername());
                updateAccounts(token, u);
                updateTransactions(token);
            } catch (RestApiException e) {
                LOGGER.error("Error while calling ext api to get accounts for " + u.getUsername(), e);
            }
        });
    }

    private void updateAccounts(String token, User user) {
        try {
            LOGGER.info("Calling ext api to get accounts:");
            List<AccountDto> accounts = extApiClient.getAccounts(token);

            for (AccountDto accountDto : accounts) {
                Account accountEntity = DtoConverter.convert(accountDto);
                accountEntity.setUser(user);
                accountService.saveAccount(accountEntity);
                LOGGER.info("Account with id " + accountEntity.getId() + " has been saved/updated.");
            }
        } catch (RestApiException e) {
            LOGGER.error("Error while calling ext api to get accounts", e);
        }
    }

    private void updateTransactions(String token) {
        try {
            LOGGER.info("Calling ext api to get transactions:");
            List<TransactionDto> transactions = extApiClient.getTransactions(token);

            List<Transaction> transactionEntities = new ArrayList<>();
            for (TransactionDto transactionDto : transactions) {
                Account account = accountService.findAccountById(transactionDto.getAccountId());
                Transaction transactionEntity = DtoConverter.convert(transactionDto);
                transactionEntity.setAccount(account);
                transactionEntities.add(transactionEntity);
                LOGGER.info("Transaction with id " + transactionEntity.getId() + " has been saved/updated.");
            }
            transactionService.saveAllTransactions(transactionEntities);
        } catch (RestApiException e) {
            LOGGER.error("Error while calling ext api to get transactions", e);
        }
    }
}
