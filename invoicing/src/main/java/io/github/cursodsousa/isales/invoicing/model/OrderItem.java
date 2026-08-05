package io.github.cursodsousa.isales.invoicing.model;

import java.math.BigDecimal;

public record OrderItem(
        Long productId,
        String productName,
        Integer amount,
        BigDecimal unitPrice
) {

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(amount));
    }
}
