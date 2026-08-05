package io.github.cursodsousa.isales.customers.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class SpringConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Jackson 3: java.time/Optional já são suportados nativamente e a configuração é via builder.
        return JsonMapper.builder()
                .changeDefaultPropertyInclusion(incl -> incl.withValueInclusion(JsonInclude.Include.NON_NULL))
                .build();
    }
}
