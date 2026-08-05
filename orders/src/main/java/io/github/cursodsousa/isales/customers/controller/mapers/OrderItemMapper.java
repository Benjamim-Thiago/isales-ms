package io.github.cursodsousa.isales.customers.controller.mapers;

import io.github.cursodsousa.isales.customers.controller.dto.OrderItemDTO;
import io.github.cursodsousa.isales.customers.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    OrderItem map(OrderItemDTO dto);
}
