package io.github.cursodsousa.isales.customers.publish.representation;

import java.math.BigDecimal;

public record OrderItemDetailRepresentation(
        Long productId,
        String productName,
        Integer amount,
        BigDecimal unitPrice
) {

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(amount));
    }
}
