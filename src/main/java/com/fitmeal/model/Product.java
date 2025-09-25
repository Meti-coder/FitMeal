package com.fitmeal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Category category;

    // Product → Tags
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Tag> tags;

    // Product → Image
    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private ProductImg productImg;
}
