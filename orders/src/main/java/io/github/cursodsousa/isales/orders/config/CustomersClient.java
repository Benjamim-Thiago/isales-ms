package io.github.cursodsousa.isales.orders.config;

import io.github.cursodsousa.isales.orders.publish.representation.CustomerRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customers", url = "${isales.order.clients.customers.url}")
public interface CustomersClient {
    @GetMapping("{id}")
    ResponseEntity<CustomerRepresentation> getCustomerById(@PathVariable("id") Long id);
}
