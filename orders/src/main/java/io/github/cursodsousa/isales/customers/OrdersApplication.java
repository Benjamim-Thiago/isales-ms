package io.github.cursodsousa.isales.customers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableFeignClients
public class OrdersApplication {
	@Value("${isales.config.kafka.topics.paid-order}")
	private String topic;
	//@Bean
	public CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> kafkaTemplate.send(
				topic, "data", "{ json }");
	}
	public static void main(String[] args) {
		SpringApplication.run(OrdersApplication.class, args);
	}

}
