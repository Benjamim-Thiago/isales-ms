package io.github.cursodsousa.isales.customers.controller.dto;

import io.github.cursodsousa.isales.customers.model.enums.PaymentType;

public record AddNewPaymentDTO(
        Long code,
        String cardData,
        PaymentType paymentType
) {
}
