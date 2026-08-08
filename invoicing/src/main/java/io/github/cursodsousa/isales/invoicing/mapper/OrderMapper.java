package io.github.cursodsousa.isales.invoicing.mapper;

import io.github.cursodsousa.isales.invoicing.model.Customer;
import io.github.cursodsousa.isales.invoicing.model.Order;
import io.github.cursodsousa.isales.invoicing.model.OrderItem;
import io.github.cursodsousa.isales.invoicing.subscribe.representation.OrderDetailRepresentation;
import io.github.cursodsousa.isales.invoicing.subscribe.representation.OrderItemDetailRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {
    public Order map(OrderDetailRepresentation representation) {
        Customer customer =  new Customer(
                representation.customerId(),
                representation.customerName(),
                representation.customerCpf(),
                representation.addressCustomerStreet(),
                representation.addressCustomerNumber(),
                representation.addressCustomerArea(),
                representation.customerEmail(),
                representation.customerPhone()
        );

        List<OrderItem> items = representation.items().stream().map(this::mapItem).toList();

        return new Order(
                representation.id(),
                representation.date(),
                representation.total(),
                customer,
                items
        );
    }

    private OrderItem mapItem(OrderItemDetailRepresentation representation) {
        return new OrderItem(
                representation.productId(),
                representation.productName(),
                representation.amount(),
                representation.unitPrice(),
                representation.total()
        );
    }
}
