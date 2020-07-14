package com.aggregation.api.service;


import com.aggregation.api.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> findAllTransactions();

    Transaction saveTransaction(Transaction transaction);

    void saveAllTransactions(List<Transaction> transactions);
}
