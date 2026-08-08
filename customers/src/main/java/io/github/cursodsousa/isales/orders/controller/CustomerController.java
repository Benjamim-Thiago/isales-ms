package io.github.cursodsousa.isales.orders.controller;

import io.github.cursodsousa.isales.orders.model.Customer;
import io.github.cursodsousa.isales.orders.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.save(customer));
    }
    @GetMapping("{id}")
    public ResponseEntity<Customer> findById(@PathVariable("id") Long id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
