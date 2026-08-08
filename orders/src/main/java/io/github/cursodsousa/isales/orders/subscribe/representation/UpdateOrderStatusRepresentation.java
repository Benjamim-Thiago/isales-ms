package io.github.cursodsousa.isales.orders.subscribe.representation;

import io.github.cursodsousa.isales.orders.model.enums.OrderStatus;

public record UpdateOrderStatusRepresentation(
        Long id,
        OrderStatus status,
        String urlInvoiceFile,
        String trackingCode
) {
}
