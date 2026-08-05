package io.github.cursodsousa.isales.customers.controller.dto;

import io.github.cursodsousa.isales.customers.model.enums.PaymentType;

public record PaymentInformation(
        String data,
        PaymentType paymentType
) {
}
