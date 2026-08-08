package io.github.cursodsousa.isales.invoicing.model;

import java.math.BigDecimal;

public class OrderItem {
    private final Long productId;
    private final String productName;
    private final Integer amount;
    private final BigDecimal unitPrice;
    private final BigDecimal total;

    public OrderItem(Long productId, String productName, Integer amount, BigDecimal unitPrice, BigDecimal total) {
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
        this.unitPrice = unitPrice;
        this.total = total;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getAmount() {
        return amount;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotal() {
        return total;
    }
}