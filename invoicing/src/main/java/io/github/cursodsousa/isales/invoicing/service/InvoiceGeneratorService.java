package io.github.cursodsousa.isales.invoicing.service;

import io.github.cursodsousa.isales.invoicing.model.Order;
import io.github.cursodsousa.isales.invoicing.publisher.InvoicingPublisher;
import io.github.cursodsousa.isales.invoicing.service.bucket.BucketFile;
import io.github.cursodsousa.isales.invoicing.service.bucket.BucketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceGeneratorService {
    private final InvoiceReportService invoiceReportService;
    private final BucketService bucketService;
    private final InvoicingPublisher invoicingPublisher;

    public void genenrate(Order order) {
        log.info("Generando nota fisacal para o pedido {}", order.id());

        try {
            byte[] byteArray = invoiceReportService.invoiceGenerator(order);

            String nameFile = String.format("notafiscal_pedido_%d.pdf", order.id());

            var file =  new BucketFile(
                    nameFile,
                    new ByteArrayInputStream(byteArray),
                    MediaType.APPLICATION_PDF,
                    byteArray.length
            );

            bucketService.upload(file);
            String url = bucketService.getUrl(nameFile);
            invoicingPublisher.publishInvoice(order, url);

            log.info("Nota fisacal, nomedo arquivo: {}", nameFile);
        } catch (Exception e) {
            log.error("Erro ao generar report pedido:  {}", e.getMessage(), e);
        }
    }
}
