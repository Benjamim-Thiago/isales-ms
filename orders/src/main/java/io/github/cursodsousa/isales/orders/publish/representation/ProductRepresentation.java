package io.github.cursodsousa.isales.orders.publish.representation;

import java.math.BigDecimal;

public record ProductRepresentation(Long id, String name, BigDecimal unitPrice) {
}
