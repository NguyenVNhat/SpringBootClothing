package com.example.NguyenVanNhat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "review_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ReviewProduct(String content, Product product) {
        this.content = content;
        this.product = product;
    }

}
