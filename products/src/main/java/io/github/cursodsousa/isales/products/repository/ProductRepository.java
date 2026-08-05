package io.github.cursodsousa.isales.products.repository;

import io.github.cursodsousa.isales.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
