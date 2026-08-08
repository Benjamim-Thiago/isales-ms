package io.github.cursodsousa.isales.orders.validator;

import feign.FeignException;
import io.github.cursodsousa.isales.orders.config.CustomersClient;
import io.github.cursodsousa.isales.orders.config.ProductsClient;
import io.github.cursodsousa.isales.orders.publish.representation.CustomerRepresentation;
import io.github.cursodsousa.isales.orders.publish.representation.ProductRepresentation;
import io.github.cursodsousa.isales.orders.model.Order;
import io.github.cursodsousa.isales.orders.model.OrderItem;
import io.github.cursodsousa.isales.orders.model.exception.ValidatorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderValidator {

    private final ProductsClient productsClient;
    private final CustomersClient customersClient;

    public void validate(Order order) {
        validateCustomer(order.getCustomerId());
        order.getOrderItems().forEach(this::validateItem);
    }

    private void validateCustomer(Long id) {
        try {
            var response =  customersClient.getCustomerById(id);
            CustomerRepresentation customer = response.getBody();
            log.debug("Cliente de codigo {} e nome {} encontrado", customer.id(), customer.name());
        } catch (FeignException.NotFound e) {
            log.error("Cliente de codigo {} nao encontrado", id);
            throw new ValidatorException("customerId", String.format("Cliente de código %d não encontrado", id));
        }
    }

    private void validateItem(OrderItem item) {
        try {
            var response =  productsClient.getProductById(item.getProductId());
            ProductRepresentation product = response.getBody();
            log.debug("Produto de codigo {} e nome {} encontrado", product.id(), product.name());
        } catch (FeignException.NotFound e) {
            log.error("Produto de codigo {} nao encontrado", item.getProductId());
            throw new ValidatorException(
                    "produtoId",
                    String.format("Produto de código %d não encontrado", item.getProductId())
            );
        }
    }
}
