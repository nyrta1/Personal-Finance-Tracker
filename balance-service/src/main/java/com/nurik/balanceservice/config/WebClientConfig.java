package com.nurik.balanceservice.config;

import com.nurik.balanceservice.client.AuthClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient authWebClient() {
        return WebClient.builder()
                .baseUrl("http://auth-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public AuthClient employeeClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(authWebClient()))
                .build();

        return httpServiceProxyFactory.createClient(AuthClient.class);
    }
}
