package io.github.cursodsousa.isales.customers.model.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String message) {
        super(message);
    }
}
