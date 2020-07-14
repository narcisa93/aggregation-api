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

import java.util.Arrays;
import java.util.Map;

@Component
public class RestApiClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiClient.class);

    private static final String USERNAME_HEADER = "username";
    private static final String X_AUTH_HEADER = "X-AUTH";
    private static final String ACCEPT_ENCODING_HEADER = "Accept-Encoding";
    private static final String ACCEPT_ENCODING_VALUE = "gzip, deflate";
    private static final String CACHE_CONTROL_VALUE = "no-cache";

    @Autowired
    RestTemplate restTemplate;

    public ResponseEntity<String> doGetRequest(String pathUrl, String token, RestApiProperties restApiProperties) throws RestApiException {
        HttpEntity<String> request = buildHttpEntityWithTokenAuth(restApiProperties, token);
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
        HttpEntity<String> request = buildHttpEntityWithUsername(restApiProperties, username);
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

    private HttpEntity<String> buildHttpEntityWithUsername(RestApiProperties properties, String username) {
        final HttpHeaders headers = buildHttpHeadersWithUsername(properties, username);
        return new HttpEntity<>(headers);
    }

    private HttpEntity<String> buildHttpEntityWithTokenAuth(RestApiProperties properties, String token) {
        final HttpHeaders headers = buildHttpHeadersWithToken(properties, token);
        return new HttpEntity<>(headers);
    }

    private HttpHeaders buildHttpHeadersWithToken(RestApiProperties properties, String token) {
        MediaType mediaType = properties.getMediaType();
        HttpHeaders headers = new HttpHeaders();
        headers.set(X_AUTH_HEADER, token);
        headers.setCacheControl(CACHE_CONTROL_VALUE);
        headers.setAccept(Arrays.asList(MediaType.ALL));
        headers.setContentType(mediaType);
        headers.set(ACCEPT_ENCODING_HEADER, ACCEPT_ENCODING_VALUE);
        return headers;
    }

    private HttpHeaders buildHttpHeadersWithUsername(RestApiProperties properties, String username) {
        MediaType mediaType = properties.getMediaType();
        HttpHeaders headers = new HttpHeaders();
        headers.set(USERNAME_HEADER, username);
        headers.setContentType(mediaType);
        return headers;
    }
}