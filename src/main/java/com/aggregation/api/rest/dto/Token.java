package com.aggregation.api.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token {
    @JsonProperty("token")
    private String tokenValue;

    public String getTokenValue() {
        return tokenValue;
    }
}
