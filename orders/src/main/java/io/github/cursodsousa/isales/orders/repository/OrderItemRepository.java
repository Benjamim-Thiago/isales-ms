package io.github.cursodsousa.isales.orders.repository;

import io.github.cursodsousa.isales.orders.model.Order;
import io.github.cursodsousa.isales.orders.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}
