package com.nurik.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic personalFinanceTrackerTopic() {
        return TopicBuilder.name("personal-finance-tracker-topic")
                .build();
    }
}