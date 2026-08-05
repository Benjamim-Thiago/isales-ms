package io.github.cursodsousa.isales.customers.config;

import io.github.cursodsousa.isales.customers.publish.representation.ProductRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "${isales.order.clients.products.url}")
public interface ProductsClient {
    @GetMapping("{id}")
    ResponseEntity<ProductRepresentation> getProductById(@PathVariable("id") Long id);
}
