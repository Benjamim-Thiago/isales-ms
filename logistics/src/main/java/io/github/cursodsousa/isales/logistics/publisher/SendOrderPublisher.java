package io.github.cursodsousa.isales.logistics.publisher;

import io.github.cursodsousa.isales.logistics.model.UpdateSendOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
@RequiredArgsConstructor
public class SendOrderPublisher {
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${isales.config.kafka.topics.sent-order}")
    private String topic;

    public void send(UpdateSendOrder updateSendOrder){
        log.info("Publicando pedido enviado");

        try {
            var json = objectMapper.writeValueAsString(updateSendOrder);
            kafkaTemplate.send(topic, "Pedido: " + updateSendOrder.id(), json);
        }catch (Exception e){
            log.error("Erro ao publica pedido, erro: {}", e.getMessage(),e);
        }
    }
}
