package com.nurik.balanceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BalanceServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(BalanceServiceApplication.class, args);
	}
}
