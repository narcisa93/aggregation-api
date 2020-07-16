package com.aggregation.api.converter;

import com.aggregation.api.model.Account;
import com.aggregation.api.model.Transaction;
import com.aggregation.api.rest.dto.*;

public class DtoConverter {

    public static Account convert(AccountDto accountDto) {
        Account account = new Account();
        account.setId(accountDto.getId());
        account.setBalance(accountDto.getBalance());
        account.setName(accountDto.getName());
        account.setProduct(accountDto.getProduct());
        account.setStatus(accountDto.getStatus());
        account.setUpdate(accountDto.getUpdate());
        account.setType(accountDto.getType());
        return account;
    }

    public static Transaction convert(TransactionDto transactionDto) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setCurrency(transactionDto.getCurrency());
        transaction.setStatus(transactionDto.getStatus());
        transaction.setUpdate(transactionDto.getUpdate());
        transaction.setDescription(transactionDto.getDescription());

        ExchangeRateDto exchangeRateDto = transactionDto.getExchangeRate();
        if (exchangeRateDto != null) {
            transaction.setExchangeRate(exchangeRateDto.getRate());
            transaction.setExchangeRateCurrencyFrom(exchangeRateDto.getCurrencyFrom());
            transaction.setExchangeRateCurrencyTo(exchangeRateDto.getCurrencyTo());
        }

        OriginalAmountDto originalAmountDto = transactionDto.getOriginalAmount();
        if (originalAmountDto != null) {
            transaction.setOriginalAmount(originalAmountDto.getAmount());
            transaction.setOriginalAmountCurrency(originalAmountDto.getCurrency());
        }

        DebtorDto debtorDto = transactionDto.getDebtor();
        if (debtorDto != null) {
            transaction.setDebtorMaskedPan(debtorDto.getMaskedPan());
            transaction.setDebtorName(debtorDto.getName());
        }

        CreditorDto creditorDto = transactionDto.getCreditor();
        if (creditorDto != null) {
            transaction.setCreditorMaskedPan(creditorDto.getMaskedPan());
            transaction.setCreditorName(creditorDto.getName());
        }
        return transaction;
    }

}
