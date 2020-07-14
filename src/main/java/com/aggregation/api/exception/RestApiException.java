package com.aggregation.api.exception;

import org.springframework.http.HttpStatus;

public class RestApiException extends Exception {

    private final HttpStatus statusCode;


    public RestApiException(String errorMessage, HttpStatus statusCode) {
        super(errorMessage);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
