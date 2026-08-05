package io.github.cursodsousa.isales.customers.model;

import io.github.cursodsousa.isales.customers.publish.representation.CustomerRepresentation;
import io.github.cursodsousa.isales.customers.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @Column(name = "total", nullable = false, precision = 16, scale = 2)
    private BigDecimal total;

    @Column(name = "payment_key")
    private String paymentKey;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column(name = "tracking_code")
    private String trackingCode;

    @Column(name = "url_nf")
    private String urlNF;

    @Transient
    private PaymentInformation paymentInformation;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @Transient
    private CustomerRepresentation customerRepresentation;
}
