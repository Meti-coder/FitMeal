package com.fitmeal.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Each item belongs to one order
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    // Each item is a product
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private double price; // price * quantity
}
