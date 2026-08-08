package io.github.cursodsousa.isales.invoicing.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${isales.config.kafka.topics.invoiced-order}")
    private String invoicedOrderTopicName;

    @Bean
    public NewTopic invoicedOrderTopic() {
        return TopicBuilder.name(invoicedOrderTopicName)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
