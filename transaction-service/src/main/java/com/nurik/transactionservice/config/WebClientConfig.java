package com.nurik.transactionservice.config;

import com.nurik.transactionservice.client.AuthClient;
import com.nurik.transactionservice.client.BalanceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.server.WebFilterChainProxy;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfig {
    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient balanceWebClient() {
        return WebClient.builder()
                .baseUrl("http://balance-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public WebClient AuthWebClient() {
        return WebClient.builder()
                .baseUrl("http://auth-service")
                .filter(filterFunction)
                .build();
    }

    @Bean
    public BalanceClient balanceClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(balanceWebClient()))
                .build();

        return httpServiceProxyFactory.createClient(BalanceClient.class);
    }

    @Bean
    public AuthClient authClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(AuthWebClient()))
                .build();

        return httpServiceProxyFactory.createClient(AuthClient.class);
    }
}
