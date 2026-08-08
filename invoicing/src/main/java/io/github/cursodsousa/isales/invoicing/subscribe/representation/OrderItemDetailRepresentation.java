package io.github.cursodsousa.isales.invoicing.subscribe.representation;

import java.math.BigDecimal;

public record OrderItemDetailRepresentation(
        Long productId,
        String productName,
        Integer amount,
        BigDecimal unitPrice,
            BigDecimal total
) {
}
