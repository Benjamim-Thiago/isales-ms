package io.github.cursodsousa.isales.orders.controller.dto;

import java.util.List;

public record NewOrderDTO(
       Long customerId,
       PaymentInformation paymentInformation,
       List<OrderItemDTO> items
) {
}
