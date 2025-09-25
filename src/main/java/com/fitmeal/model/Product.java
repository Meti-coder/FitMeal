package com.fitmeal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(name = "r_cre_time")
    private LocalDateTime createdTime = LocalDateTime.now();

    @Column(name = "r_mod_time")
    private LocalDateTime modifiedTime;

    // Product → Category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Product → Tags
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

    // Product → Image
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductImg productImg;
}
