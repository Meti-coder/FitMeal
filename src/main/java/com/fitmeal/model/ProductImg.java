package com.fitmeal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_img")
public class ProductImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @Column(nullable = false)
    private String url;

    @OneToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore // prevent recursion in JSON
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Product product;
}
