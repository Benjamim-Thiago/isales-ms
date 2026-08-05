package io.github.cursodsousa.isales.customers.service;

import io.github.cursodsousa.isales.customers.config.CustomersClient;
import io.github.cursodsousa.isales.customers.config.ProductsClient;
import io.github.cursodsousa.isales.customers.external.BankClientService;
import io.github.cursodsousa.isales.customers.model.Order;
import io.github.cursodsousa.isales.customers.model.OrderItem;
import io.github.cursodsousa.isales.customers.model.PaymentInformation;
import io.github.cursodsousa.isales.customers.model.enums.OrderStatus;
import io.github.cursodsousa.isales.customers.model.enums.PaymentType;
import io.github.cursodsousa.isales.customers.model.exception.ItemNotFoundException;
import io.github.cursodsousa.isales.customers.publish.PaymentPublisher;
import io.github.cursodsousa.isales.customers.repository.OrderItemRepository;
import io.github.cursodsousa.isales.customers.repository.OrderRepository;
import io.github.cursodsousa.isales.customers.validator.OrderValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderValidator validator;
    private final BankClientService bankClientService;
    private final CustomersClient apiCustomer;
    private final ProductsClient apiProduct;
    private final PaymentPublisher paymentPublisher;

    @Transactional
    public Order save(Order order) {
        validator.validate(order);
        persistenceValues(order);
        sendRequestPayment(order);
        return order;
    }

    private void sendRequestPayment(Order order) {
        String key = bankClientService.requestPayment(order);
        order.setPaymentKey(key);
    }

    private void persistenceValues(Order order) {
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getOrderItems());
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public void updatePaymentStatus(
            Long orderCode,
            String paymentKey,
            Boolean success,
            String notes
    ) {
        var findOrder = orderRepository.findByIdAndPaymentKey(orderCode, paymentKey);

        if (findOrder.isEmpty()) {
            log.error(String.format("Order code %d and payment key %s not found", orderCode, paymentKey));
            return;
        }
        Order order = findOrder.get();

        if (Boolean.TRUE.equals(success)) {
            prepareAndPublishPaidOrder(order);
        } else {
            order.setStatus(OrderStatus.ERRO_PAGAMENTO);
            order.setNotes(notes);
        }

        orderRepository.save(order);
    }

    private void prepareAndPublishPaidOrder(Order order) {
        order.setStatus(OrderStatus.PAGO);
        loadCustomer(order);
        loadItems(order);
        paymentPublisher.publish(order);
    }

    @Transactional
    public void addNewPaymentType(
            Long code,
            String dataCard,
            PaymentType PaymentType
    ) {
        var findOrder = orderRepository.findById(code);

        if(findOrder.isEmpty()) {
            throw new ItemNotFoundException("Pedido não encontrado para código fornecido.");
        }

        var order = findOrder.get();

        PaymentInformation  paymentInformation = new PaymentInformation();
        paymentInformation.setPaymentType(PaymentType);
        paymentInformation.setData(dataCard);

        try {
            order.setPaymentInformation(paymentInformation);
            order.setStatus(OrderStatus.REALIZADO);
            order.setNotes("Novo metodo de pagamento adicionado");
            log.info("Novo metodo de pagamento adicionado");
            orderRepository.save(order);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Order> findFullOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(this::loadCustomer);
        order.ifPresent(this::loadItems);
        return order;
    }

    private void loadCustomer(Order order) {
        var response = apiCustomer.getCustomerById(order.getCustomerId());
        order.setCustomerRepresentation(response.getBody());
    }

    private void loadItems(Order order) {
        List<OrderItem> items = orderItemRepository.findByOrder(order);
        order.setOrderItems(items);
        order.getOrderItems().forEach(this::loadProductData);
    }

    private void loadProductData(OrderItem item) {
        var response = apiProduct.getProductById(item.getProductId());
        item.setProductName(Objects.requireNonNull(response.getBody()).name());

    }
}
