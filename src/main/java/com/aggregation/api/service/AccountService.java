package com.aggregation.api.service;

import com.aggregation.api.model.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAllAccounts();

    Account saveAccount(Account account);

    Account findAccountById(String id);
}
