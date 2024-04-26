package com.google.workstreams.bqendpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BqendpointApplication {

	public static void main(String[] args) {
		SpringApplication.run(BqendpointApplication.class, args);

		System.out.println("Java version: " + System.getProperty("java.version"));
	}

}
