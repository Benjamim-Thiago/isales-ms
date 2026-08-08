package io.github.cursodsousa.isales.orders.controller.dto;

import io.github.cursodsousa.isales.orders.model.enums.PaymentType;

public record PaymentInformation(
        String data,
        PaymentType paymentType
) {
}
