# Aggregation REST API

Aggregation REST API is a Spring Boot application that exposes account and transaction data from a H2 database. The database is updated once a day from a web service. The application uses Spring Security for authentication and the main user is created on application start-up.

## Requirements

For building and running the application you need:

- JDK 1.8
- Maven

## Running the application locally

You can run the application by executing the `main` method in the `com.aggregation.api.application.AggregationApiApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like this:

```shell
mvn spring-boot:run
```

The application starts on port 8081 and it exposes 2 endpoints:
- http://localhost:8081/accounts

```sh
curl -X GET 'http://localhost:8081/accounts' \
  -H 'Connection: keep-alive' \
  -H 'Cache-Control: max-age=0' \
  -H 'Authorization: Basic aW9uZXNjdTpwYXNzd29yZA==' \
  -H 'Upgrade-Insecure-Requests: 1' \
  -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36' \
  -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
  -H 'Sec-Fetch-Site: none' \
  -H 'Sec-Fetch-Mode: navigate' \
  -H 'Sec-Fetch-User: ?1' \
  -H 'Sec-Fetch-Dest: document' \
  -H 'Accept-Language: en,ro-RO;q=0.9,ro;q=0.8,en-US;q=0.7' \
  -H 'Cookie: JSESSIONID=218808EFD03077FD9C710BDF4F121AE1' \
```
- http://localhost:8081/transactions

```sh
curl -X GET 'http://localhost:8081/transactions' \
  -H 'Connection: keep-alive' \
  -H 'Cache-Control: max-age=0' \
  -H 'Authorization: Basic aW9uZXNjdTpwYXNzd29yZA==' \
  -H 'Upgrade-Insecure-Requests: 1' \
  -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.116 Safari/537.36' \
  -H 'Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9' \
  -H 'Sec-Fetch-Site: none' \
  -H 'Sec-Fetch-Mode: navigate' \
  -H 'Sec-Fetch-User: ?1' \
  -H 'Sec-Fetch-Dest: document' \
  -H 'Accept-Language: en,ro-RO;q=0.9,ro;q=0.8,en-US;q=0.7' \
  -H 'Cookie: JSESSIONID=218808EFD03077FD9C710BDF4F121AE1' \
```

Authentication is required. A test user is created by default:
```properties
username: ionescu
password: test124
```

There is a cron job configured to update the database (by calling an external api) that will run at 12:00 PM every day. The cron expression is configured in application.properties:

```properties
scheduler.cron.expression=0 0 12 * * ?
```

## Running tests

You can run the tests in package `com.aggregation.api.unittests` from your IDE or using maven:

```shell
mvn test
```