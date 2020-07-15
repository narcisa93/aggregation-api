package com.aggregation.api.service;

import com.aggregation.api.model.Account;
import com.aggregation.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAllAccounts() {
        return (List<Account>) accountRepository.findAll();
    }

    @Override
    public List<Account> findAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findAccountById(String id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.orElse(null);
    }
}
