package io.github.cursodsousa.isales.orders.repository;

import io.github.cursodsousa.isales.orders.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
