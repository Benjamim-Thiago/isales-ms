package io.github.cursodsousa.isales.orders.service;

import io.github.cursodsousa.isales.orders.model.enums.OrderStatus;
import io.github.cursodsousa.isales.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateOrderStatusService {
    private final OrderRepository orderRepository;

    @Transactional
    public void updateStatus(Long id, OrderStatus orderStatus, String urlInvoiceFile, String trackingCode) {
        orderRepository.findById(id).ifPresent(order -> {
            order.setStatus(orderStatus);
            if (urlInvoiceFile != null) order.setUrlNF(urlInvoiceFile);
            if (trackingCode != null) order.setTrackingCode(trackingCode);
        });
    }
}
