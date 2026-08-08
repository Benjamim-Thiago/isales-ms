package io.github.cursodsousa.isales.orders.controller.dto;

import io.github.cursodsousa.isales.orders.model.enums.PaymentType;

public record AddNewPaymentDTO(
        Long code,
        String cardData,
        PaymentType paymentType
) {
}
