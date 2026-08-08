package io.github.cursodsousa.isales.invoicing.publisher;

import io.github.cursodsousa.isales.invoicing.model.Order;
import io.github.cursodsousa.isales.invoicing.model.enums.OrderStatus;
import io.github.cursodsousa.isales.invoicing.publisher.representation.UpdateOrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class InvoicingPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${isales.config.kafka.topics.invoiced-order}")
    private String topic;

    public void publishInvoice(Order order, String urlInvoice) {
        try {
            var representation =  new UpdateOrderStatus(order.id(), OrderStatus.FATURADO, urlInvoice);
            String json = objectMapper.writeValueAsString(representation);
            kafkaTemplate.send(topic, "Order ID " + order.id(), json);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
