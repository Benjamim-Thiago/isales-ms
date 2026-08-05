package io.github.cursodsousa.isales.customers.controller.dto;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        Integer amount,
        BigDecimal unitPrice
) {
}
