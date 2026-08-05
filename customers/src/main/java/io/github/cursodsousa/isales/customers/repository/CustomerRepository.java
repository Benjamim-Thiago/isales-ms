package io.github.cursodsousa.isales.customers.repository;

import io.github.cursodsousa.isales.customers.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
