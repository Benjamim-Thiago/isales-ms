package io.github.cursodsousa.isales.orders.publish;

import io.github.cursodsousa.isales.orders.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentPublisher {
    private final OrderDetailMapper mapper;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${isales.config.kafka.topics.paid-order}")
    private String topic;

    public void publish(Order order){
        log.info("Publicando pedio pago {}", order.getId());
        try {
            var representation = mapper.map(order);
            var json = objectMapper.writeValueAsString(representation);
            kafkaTemplate.send(topic, "data", json);
        } catch (JacksonException e) {
            log.error("Erro ao processar o json", e);
        } catch (RuntimeException e) {
            log.error("Erro tecnico ao publicar no tópico de pedidos", e);
        }
    }
}
