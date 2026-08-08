package io.github.cursodsousa.isales.orders.controller.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        Integer amount,
        BigDecimal unitPrice
) {
}
