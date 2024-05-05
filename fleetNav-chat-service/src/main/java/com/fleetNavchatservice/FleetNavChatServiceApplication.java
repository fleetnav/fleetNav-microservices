package com.fleetNavchatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FleetNavChatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FleetNavChatServiceApplication.class, args);
	}

}
