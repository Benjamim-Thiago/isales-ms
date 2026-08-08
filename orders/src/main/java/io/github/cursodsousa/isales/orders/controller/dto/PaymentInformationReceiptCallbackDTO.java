package io.github.cursodsousa.isales.orders.controller.dto;

public record PaymentInformationReceiptCallbackDTO(
        Long code,
        String paymentKey,
        Boolean status,
        String notes
) {
}
