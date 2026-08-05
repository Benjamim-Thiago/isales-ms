package io.github.cursodsousa.isales.customers.publish.representation;

public record CustomerRepresentation(
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
