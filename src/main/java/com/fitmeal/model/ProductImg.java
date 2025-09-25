package com.fitmeal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product_img")
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @Column(nullable = false)
    private String url;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore // prevent recursion
    private Product product;
}
