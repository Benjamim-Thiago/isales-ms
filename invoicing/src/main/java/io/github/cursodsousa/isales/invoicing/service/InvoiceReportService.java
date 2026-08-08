package io.github.cursodsousa.isales.invoicing.service;

import io.github.cursodsousa.isales.invoicing.model.Order;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class InvoiceReportService {
    @Value("classpath:reports/invoice_file.jrxml")
    private Resource invoiceFile;

    @Value("classpath:reports/logo.png")
    private Resource logo;

    public byte[] invoiceGenerator(Order order) {
        try(InputStream inputStream = invoiceFile.getInputStream()) {
            Map<String, Object> params = new HashMap<>();
            params.put("ID", order.id());
            params.put("DATE", parseToDate(order));
            params.put("CUSTOMER_NAME", order.customer().name());
            params.put("CUSTOMER_CPF", order.customer().document());
            params.put("ADDRESS_CUSTOMER_STREET", order.customer().street());
            params.put("ADDRESS_CUSTOMER_NUMBER", order.customer().number());
            params.put("ADDRESS_CUSTOMER_AREA", order.customer().area());
            params.put("CUSTOMER_EMAIL", order.customer().email());
            params.put("CUSTOMER_PHONE", order.customer().phone());
            params.put("TOTAL", order.total());
            params.put("LOGO", logo.getFile().getAbsolutePath());

            var dataSource = new JRBeanCollectionDataSource(order.items());

            JasperReport  jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

            return JasperExportManager.exportReportToPdf(jasperPrint);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Date parseToDate(Order order) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dataUtil = formato.parse(order.date()); // lança ParseException (checked)
        return  new Date(dataUtil.getTime());
    }
}
