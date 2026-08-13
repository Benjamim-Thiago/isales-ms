package io.github.cursodsousa.isales.logistics.subscribe.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cursodsousa.isales.logistics.model.enums.OrderStatus;

public record UpdateInvoicingRepresentation(
        Long id,
        @JsonProperty("orderStatus") OrderStatus status,
        @JsonProperty("urlNotaFiscal") String urlInvoiceFile,
        String trackingCode
) {
}
