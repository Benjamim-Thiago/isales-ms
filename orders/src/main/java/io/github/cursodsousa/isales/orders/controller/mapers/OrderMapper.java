package io.github.cursodsousa.isales.orders.controller.mapers;

import io.github.cursodsousa.isales.orders.controller.dto.NewOrderDTO;
import io.github.cursodsousa.isales.orders.controller.dto.OrderItemDTO;
import io.github.cursodsousa.isales.orders.model.Order;
import io.github.cursodsousa.isales.orders.model.OrderItem;
import io.github.cursodsousa.isales.orders.model.enums.OrderStatus;
import org.jspecify.annotations.NonNull;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderItemMapper ORDER_ITEM_MAPPER = Mappers.getMapper(OrderItemMapper.class);

    @Mapping(source = "items", target = "orderItems", qualifiedByName = "mapItems")
    @Mapping(source = "paymentInformation", target = "paymentInformation")
    Order map(NewOrderDTO dto);

    @Named("mapItems")
    default List<OrderItem> map( List<OrderItemDTO> dtos) {
        return  dtos.stream().map(ORDER_ITEM_MAPPER::map).toList();
    }

    @AfterMapping
    default void afterMapping(@MappingTarget Order order) {
        order.setStatus(OrderStatus.REALIZADO);
        order.setDate(LocalDateTime.now());

        var total = calculateTotal(order);
        order.setTotal(total);
        order.getOrderItems().forEach(orderItem -> orderItem.setOrder(order));

    }

    private static @NonNull BigDecimal calculateTotal(Order order) {
        return order.getOrderItems().stream().map(item -> {
            return item.getUnitPrice().multiply(BigDecimal.valueOf(item.getAmount()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add).abs();
    }
}
