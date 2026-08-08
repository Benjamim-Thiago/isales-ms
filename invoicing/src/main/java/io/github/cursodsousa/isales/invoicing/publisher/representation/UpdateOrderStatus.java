package io.github.cursodsousa.isales.invoicing.publisher.representation;

import io.github.cursodsousa.isales.invoicing.model.enums.OrderStatus;

public record UpdateOrderStatus(Long id, OrderStatus orderStatus, String urlNotaFiscal) {

}
