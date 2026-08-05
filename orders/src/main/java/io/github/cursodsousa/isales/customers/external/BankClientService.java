package io.github.cursodsousa.isales.customers.external;

import io.github.cursodsousa.isales.customers.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class BankClientService {
    public String requestPayment(Order order) {
        log.info("solcitando pagamento para pedido de código {}", order.getId());
        return UUID.randomUUID().toString();
    }
}
