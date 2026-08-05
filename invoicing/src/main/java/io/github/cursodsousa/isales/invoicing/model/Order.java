package io.github.cursodsousa.isales.invoicing.model;

import java.math.BigDecimal;
import java.util.List;

public record Order(
        Long id,
        String date,
        BigDecimal total,
        Customer customer,
        List<OrderItem> items
) {

}
