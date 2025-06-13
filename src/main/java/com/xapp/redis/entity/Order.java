package com.xapp.redis.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "orders")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "product_id", nullable = false)
    private Long productId;
    @Column(name="quantity", nullable = false)
    private int quantity;
}
