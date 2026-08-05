package io.github.cursodsousa.isales.customers.publish.representation;

import java.math.BigDecimal;

public record ProductRepresentation(Long id, String name, BigDecimal unitPrice) {
}
