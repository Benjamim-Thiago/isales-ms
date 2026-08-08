package io.github.cursodsousa.isales.orders.service;

import io.github.cursodsousa.isales.orders.model.enums.OrderStatus;
import io.github.cursodsousa.isales.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusService {
    private final OrderRepository orderRepository;

    public void updateStatus(Long id, OrderStatus orderStatus, String urlInvoiceFile, String trackingCode) {

    }
}
