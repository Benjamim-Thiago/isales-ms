package io.github.cursodsousa.isales.orders.subscribe.representation;

import io.github.cursodsousa.isales.orders.model.enums.OrderStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateOrderStatusRepresentation(
        Long id,
        @JsonProperty("orderStatus") OrderStatus status,
        @JsonProperty("urlNotaFiscal") String urlInvoiceFile,
        String trackingCode
) {
}
