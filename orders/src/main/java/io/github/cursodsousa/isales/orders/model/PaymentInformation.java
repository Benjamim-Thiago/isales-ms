package io.github.cursodsousa.isales.orders.model;

import io.github.cursodsousa.isales.orders.model.enums.PaymentType;
import lombok.Data;

@Data
public class PaymentInformation {
    private String data;
    private PaymentType paymentType;
}

