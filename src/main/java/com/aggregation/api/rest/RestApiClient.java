package com.aggregation.api.rest;

import com.aggregation.api.exception.RestApiException;
import com.aggregation.api.properties.RestApiProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

@Component
public class RestApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiClient.class);

    private static final String USERNAME_HEADER = "username";
    private static final String X_AUTH_HEADER = "X-AUTH";
    private static final String ACCEPT_ENCODING_HEADER = "Accept-Encoding";
    private static final String ACCEPT_ENCODING_VALUE = "gzip, deflate";
    private static final String CACHE_CONTROL_VALUE = "no-cache";
    private static final MediaType MEDIA_TYPE_APPLICATION_JSON = MediaType.APPLICATION_JSON;

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> doGetRequest(String pathUrl, String token, RestApiProperties restApiProperties) throws RestApiException {
        HttpEntity<String> request = buildHttpEntityWithTokenAuth(token);
        ResponseEntity<String> restResponse;
        try {
            String operationPath = buildPath(restApiProperties, pathUrl);
            restResponse = restTemplate.exchange(operationPath, HttpMethod.GET, request, String.class);

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.error("GET request failed: " + e.getMessage(), e);
            throw new RestApiException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            LOGGER.error("GET request failed: " + e.getMessage(), e);
            throw new RestApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!restResponse.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("GET request failed: " + restResponse.toString() + " [errorCode]: " + restResponse.getStatusCode());
            throw new RestApiException(restResponse.toString(), restResponse.getStatusCode());
        }
        return restResponse;
    }

    public ResponseEntity<String> doPostRequest(String pathUrl, String username, RestApiProperties restApiProperties) throws RestApiException {
        HttpEntity<String> request = buildHttpEntityWithUsername(username);
        ResponseEntity<String> restResponse;
        try {
            String operationPath = buildPath(restApiProperties, pathUrl);
            restResponse = restTemplate.postForEntity(operationPath, request, String.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            LOGGER.error("POST request failed: " + e.getMessage(), e);
            throw new RestApiException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            LOGGER.error("POST request failed: " + e.getMessage(), e);
            throw new RestApiException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (!restResponse.getStatusCode().is2xxSuccessful()) {
            LOGGER.error("POST request failed: " + restResponse.toString() + " [errorCode]: " + restResponse.getStatusCode());
            throw new RestApiException(restResponse.toString(), restResponse.getStatusCode());
        }
        return restResponse;
    }

    private String buildPath(RestApiProperties properties, String pathUrl) {
        return UriComponentsBuilder.newInstance()
                .scheme(properties.getProtocol())
                .host(properties.getHost())
                .port(properties.getPort())
                .path(pathUrl)
                .toUriString();
    }

    private HttpEntity<String> buildHttpEntityWithUsername(String username) {
        final HttpHeaders headers = buildHttpHeadersWithUsername(username);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<String> buildHttpEntityWithTokenAuth(String token) {
        final HttpHeaders headers = buildHttpHeadersWithToken(token);
        return new HttpEntity<>(headers);
    }

    private HttpHeaders buildHttpHeadersWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_AUTH_HEADER, token);
        headers.setCacheControl(CACHE_CONTROL_VALUE);
        headers.setAccept(Collections.singletonList(MediaType.ALL));
        headers.setContentType(MEDIA_TYPE_APPLICATION_JSON);
        headers.set(ACCEPT_ENCODING_HEADER, ACCEPT_ENCODING_VALUE);
        return headers;
    }

    private HttpHeaders buildHttpHeadersWithUsername(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(USERNAME_HEADER, username);
        headers.setContentType(MEDIA_TYPE_APPLICATION_JSON);
        return headers;
    }
}