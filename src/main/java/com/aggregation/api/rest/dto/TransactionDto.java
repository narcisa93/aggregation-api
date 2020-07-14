package com.aggregation.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "accountId",
        "exchangeRate",
        "originalAmount",
        "creditor",
        "debtor",
        "status",
        "currency",
        "amount",
        "update",
        "description"
})
public class TransactionDto {

    @JsonProperty("id")
    private String id;
    @JsonProperty("accountId")
    private String accountId;
    @JsonProperty("exchangeRate")
    private ExchangeRateDto exchangeRate;
    @JsonProperty("originalAmount")
    private OriginalAmountDto originalAmount;
    @JsonProperty("creditor")
    private CreditorDto creditor;
    @JsonProperty("debtor")
    private DebtorDto debtor;
    @JsonProperty("status")
    private String status;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("update")
    private String update;
    @JsonProperty("description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public ExchangeRateDto getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(ExchangeRateDto exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public OriginalAmountDto getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(OriginalAmountDto originalAmount) {
        this.originalAmount = originalAmount;
    }

    public CreditorDto getCreditor() {
        return creditor;
    }

    public void setCreditor(CreditorDto creditor) {
        this.creditor = creditor;
    }

    public DebtorDto getDebtor() {
        return debtor;
    }

    public void setDebtor(DebtorDto debtor) {
        this.debtor = debtor;
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
}
