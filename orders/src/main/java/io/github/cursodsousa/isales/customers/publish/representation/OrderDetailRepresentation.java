package io.github.cursodsousa.isales.customers.publish.representation;

import io.github.cursodsousa.isales.customers.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderDetailRepresentation(
        Long id,
        String date,
        BigDecimal total,
        OrderStatus status,
        Long customerId,
        String customerName,
        String customerCpf,
        String addressCustomerStreet,
        String  addressCustomerNumber,
        String  addressCustomerArea,
        String customerEmail,
        String customerPhone,
        List<OrderItemDetailRepresentation> items
) {

}
