package io.github.cursodsousa.isales.logistics.subscribe;

import io.github.cursodsousa.isales.logistics.service.SendOrderService;
import io.github.cursodsousa.isales.logistics.subscribe.representation.UpdateInvoicingRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
@Slf4j
@RequiredArgsConstructor
public class InvoicingSubscribe {
    private final ObjectMapper objectMapper;
    private final SendOrderService sendOrderService;

    @KafkaListener(groupId = "${spring.Kafka.consumer.group-id}",
            topics = "${isales.config.kafka.topics.invoiced-order}")
    public void listen(String json){
        try {
            log.info("Recebendo pedido para envio: {}", json);

            var representation = objectMapper.readValue(json, UpdateInvoicingRepresentation.class);
            sendOrderService.send(representation.id(), representation.urlInvoiceFile());
            log.info("Pedido processado com sucesso! Código de pedido: {}", representation.id());
        } catch (Exception e) {
            log.error("Erro ao preparar  para envio: {}", e.getMessage(), e);
        }
    }
}
