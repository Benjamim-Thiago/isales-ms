package io.github.cursodsousa.isales.invoicing.subscribe;

import io.github.cursodsousa.isales.invoicing.mapper.OrderMapper;
import io.github.cursodsousa.isales.invoicing.service.InvoiceGeneratorService;
import io.github.cursodsousa.isales.invoicing.subscribe.representation.OrderDetailRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaidOrderSubscribe {
    private final ObjectMapper objectMapper;
    private final InvoiceGeneratorService invoiceGeneratorService;
    private final OrderMapper orderMapper;

    @KafkaListener(groupId = "${spring.Kafka.consumer.group-id}",
        topics = "${isales.config.kafka.topics.paid-order}")
    public void listen(String json) {
            try {
                log.info("PaidOrderSubscribe received : {}", json);
                var representation = objectMapper.readValue(json, OrderDetailRepresentation.class);
                invoiceGeneratorService.genenrate(orderMapper.map(representation));
            } catch (Exception e) {
                log.error("Erro na consumação do topico de pedidos pagos: " + e.getMessage());
            }
    }
}
