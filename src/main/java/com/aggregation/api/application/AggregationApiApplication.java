package com.aggregation.api.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan("com.aggregation.api")
@EnableJpaRepositories("com.aggregation.api")
@EntityScan("com.aggregation.api.model")
@EnableScheduling
public class AggregationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AggregationApiApplication.class, args);
	}

}
