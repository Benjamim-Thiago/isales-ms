package io.github.cursodsousa.isales.customers.controller;

import io.github.cursodsousa.isales.customers.controller.dto.PaymentInformationReceiptCallbackDTO;
import io.github.cursodsousa.isales.customers.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders/payments-callback")
public class PaymentReceiptCallbackController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Object> updatePaymentStatus(
            @RequestBody PaymentInformationReceiptCallbackDTO body,
            @RequestHeader(required = true, name = "api-key") String apikey
    ) {
        orderService.updatePaymentStatus(body.code(), body.paymentKey(), body.status(), body.notes());

        return ResponseEntity.ok().build();
    }
}
