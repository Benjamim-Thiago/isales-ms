package io.github.cursodsousa.isales.customers.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {
        "io.github.cursodsousa.isales.orders.config"
})
public class ClientsConfig {
}
