package com.aggregation.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transaction {
    @Id
    private String id;

    private String status;
    private String currency;
    private Double amount;
    private String update;
    private String description;
    private Double exchangeRate;
    private String exchangeRateCurrencyFrom;
    private String exchangeRateCurrencyTo;
    private String debtorMaskedPan;
    private String debtorName;
    private String creditorMaskedPan;
    private String creditorName;
    private Double originalAmount;
    private String originalAmountCurrency;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Transaction() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(Double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getExchangeRateCurrencyFrom() {
        return exchangeRateCurrencyFrom;
    }

    public void setExchangeRateCurrencyFrom(String exchangeRateCurrencyFrom) {
        this.exchangeRateCurrencyFrom = exchangeRateCurrencyFrom;
    }

    public String getExchangeRateCurrencyTo() {
        return exchangeRateCurrencyTo;
    }

    public void setExchangeRateCurrencyTo(String exchangeRateCurrencyTo) {
        this.exchangeRateCurrencyTo = exchangeRateCurrencyTo;
    }

    public String getDebtorMaskedPan() {
        return debtorMaskedPan;
    }

    public void setDebtorMaskedPan(String debtorMaskedPan) {
        this.debtorMaskedPan = debtorMaskedPan;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public String getCreditorMaskedPan() {
        return creditorMaskedPan;
    }

    public void setCreditorMaskedPan(String creditorMaskedPan) {
        this.creditorMaskedPan = creditorMaskedPan;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getOriginalAmountCurrency() {
        return originalAmountCurrency;
    }

    public void setOriginalAmountCurrency(String originalAmountCurrency) {
        this.originalAmountCurrency = originalAmountCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transaction that = (Transaction) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

