package io.github.cursodsousa.isales.customers.repository;

import io.github.cursodsousa.isales.customers.model.Order;
import io.github.cursodsousa.isales.customers.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrder(Order order);
}
