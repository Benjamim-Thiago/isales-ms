package io.github.cursodsousa.isales.invoicing.model;

public record Customer(
        Long id,
        String name,
        String document,
        String street,
        String number,
        String area,
        String email,
        String phone
) {
}
