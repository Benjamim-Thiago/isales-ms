package io.github.cursodsousa.isales.orders.controller.mapers;

import io.github.cursodsousa.isales.orders.controller.dto.OrderItemDTO;
import io.github.cursodsousa.isales.orders.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem map(OrderItemDTO dto);
}
