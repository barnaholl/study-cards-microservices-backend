package com.codecool.deckhandlerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DeckHandlerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeckHandlerServiceApplication.class, args);
	}

}
