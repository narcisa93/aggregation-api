package com.aggregation.api.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RestApiProperties {

    @Value ("${ext-api.protocol}")
    private String protocol;

    @Value("${ext-api.host}")
    private String host;

    @Value("${ext-api.port}")
    private String port;

    @Value("${ext-api.login.path}")
    private String loginPath;

    @Value("${ext-api.accounts.path}")
    private String accountsPath;

    @Value("${ext-api.transactions.path}")
    private String transactionsPath;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public void setLoginPath(String loginPath) {
        this.loginPath = loginPath;
    }

    public String getAccountsPath() {
        return accountsPath;
    }

    public void setAccountsPath(String accountsPath) {
        this.accountsPath = accountsPath;
    }

    public String getTransactionsPath() {
        return transactionsPath;
    }

    public void setTransactionsPath(String transactionsPath) {
        this.transactionsPath = transactionsPath;
    }
}
