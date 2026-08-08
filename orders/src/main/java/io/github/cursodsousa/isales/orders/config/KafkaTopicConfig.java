package io.github.cursodsousa.isales.orders.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${isales.config.kafka.topics.sent-order}")
    private String sentOrderTopicName;

    @Value("${isales.config.kafka.topics.paid-order}")
    private String paidOrderTopicName;

    @Bean
    public NewTopic orderSentTopic() {
        return TopicBuilder.name(sentOrderTopicName)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic paidOrderTopic() {
        return TopicBuilder.name(paidOrderTopicName)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
