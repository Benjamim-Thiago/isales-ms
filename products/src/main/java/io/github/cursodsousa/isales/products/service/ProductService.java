package io.github.cursodsousa.isales.products.service;

import io.github.cursodsousa.isales.products.model.Product;
import io.github.cursodsousa.isales.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        product.setActive(true);
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void delete(Product product) {
        product.setActive(false);
        productRepository.save(product);
    }
}
