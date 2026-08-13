package io.github.cursodsousa.isales.orders.publish.representation;

import io.github.cursodsousa.isales.orders.model.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

public record OrderDetailRepresentation(
        Long id,
        String date,
        BigDecimal total,
        OrderStatus status,
        String trackingCode,
        String urlInvoiceFile,
        Long customerId,
        String customerName,
        String customerCpf,
        String addressCustomerStreet,
        String addressCustomerNumber,
        String addressCustomerArea,
        String customerEmail,
        String customerPhone,
        List<OrderItemDetailRepresentation> items
) {

}
