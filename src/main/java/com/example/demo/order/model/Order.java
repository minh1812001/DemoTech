package com.example.demo.order.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "orders", schema = "test_performance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(name = "order_seq", sequenceName = "test_performance.orders_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

    private Integer quantity;

    private BigDecimal price;

    private String status;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "created_at")
    private String createdAt; // Bạn có thể đổi thành LocalDateTime nếu cần

    @Column(name = "updated_at")
    private String updatedAt;
}
