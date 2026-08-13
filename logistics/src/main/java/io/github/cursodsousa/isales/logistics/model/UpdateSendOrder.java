package io.github.cursodsousa.isales.logistics.model;

import io.github.cursodsousa.isales.logistics.model.enums.OrderStatus;

public record UpdateSendOrder(
        Long id,
        OrderStatus orderStatus,
        String urlNotaFiscal,
        String trackingCode
) {
}
