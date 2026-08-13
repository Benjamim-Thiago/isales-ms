package io.github.cursodsousa.isales.logistics.service;

import io.github.cursodsousa.isales.logistics.model.UpdateSendOrder;
import io.github.cursodsousa.isales.logistics.model.enums.OrderStatus;
import io.github.cursodsousa.isales.logistics.publisher.SendOrderPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class SendOrderService {
    private final SendOrderPublisher sendOrderPublisher;

    public void send(Long id, String invoicedFileLink){
        var trackingCode = generateTrackingCode();
        var representation = new UpdateSendOrder(
                id, OrderStatus.ENVIADO, invoicedFileLink, trackingCode);

        sendOrderPublisher.send(representation);
    }

    private String generateTrackingCode() {
        var random = new Random();

        char firstLetter = (char) ('A' + random.nextInt(26));
        char secondLetter = (char) ('A' + random.nextInt(26));
        int number = 1000000000 + random.nextInt(900000000);

        return "" + firstLetter + secondLetter + number + "BR";
    }
}
