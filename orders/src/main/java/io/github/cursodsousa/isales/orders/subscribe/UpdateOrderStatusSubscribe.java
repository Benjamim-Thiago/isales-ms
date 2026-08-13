package io.github.cursodsousa.isales.orders.subscribe;

import io.github.cursodsousa.isales.orders.service.UpdateOrderStatusService;
import io.github.cursodsousa.isales.orders.subscribe.representation.UpdateOrderStatusRepresentation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateOrderStatusSubscribe {
    private final UpdateOrderStatusService updateOrderStatusService;
    private final ObjectMapper objectMapper;

    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}", topics = {
            "${isales.config.kafka.topics.invoiced-order}",
            "${isales.config.kafka.topics.sent-order}"
    })
    public void receiveUpdate(String payload) {
        try{
            log.info("Recebendo atualização de status: {}", payload);
            var updateStatus = objectMapper.readValue(payload, UpdateOrderStatusRepresentation.class);
            updateOrderStatusService.updateStatus(
                    updateStatus.id(),
                    updateStatus.status(),
                    updateStatus.urlInvoiceFile(),
                    updateStatus.trackingCode()
            );
            log.info("Pedido atualizado com sucesso!");
        } catch (Exception e){
            log.error("Erro ao atualizar status de pedido: {}", e.getMessage());
        }
    }
}
