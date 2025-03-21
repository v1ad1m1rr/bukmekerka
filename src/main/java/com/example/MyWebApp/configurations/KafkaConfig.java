package com.example.MyWebApp.configurations;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

//    @Bean
//    public NewTopic newTopic() {
//        return TopicBuilder.name("notification").build();
//    }
}
