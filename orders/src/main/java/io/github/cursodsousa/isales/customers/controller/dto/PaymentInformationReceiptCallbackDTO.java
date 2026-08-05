package io.github.cursodsousa.isales.customers.controller.dto;

public record PaymentInformationReceiptCallbackDTO(
        Long code,
        String paymentKey,
        Boolean status,
        String notes
) {
}
