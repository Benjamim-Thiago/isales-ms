package io.github.cursodsousa.isales.customers.model.exception;

import lombok.Getter;

@Getter
public class ValidatorException extends RuntimeException {
    private String field;
    private String message;

    public ValidatorException(String field, String message) {
        super(message);
        this.field = field;
        this.message = message;
    }
}
