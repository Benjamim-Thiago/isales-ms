package io.github.cursodsousa.isales.invoicing.subscribe.representation;

import java.math.BigDecimal;
import java.util.List;

public record OrderDetailRepresentation(
        Long id,
        String date,
        BigDecimal total,
        Long customerId,
        String customerName,
        String customerCpf,
        String addressCustomerStreet,
        String addressCustomerNumber,
        String addressCustomerArea,
        String customerEmail,
        String customerPhone,
        List<OrderItemDetailRepresentation> items
) {

}
