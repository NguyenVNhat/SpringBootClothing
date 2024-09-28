package com.example.NguyenVanNhat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "image_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = Integer.MAX_VALUE)
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ImageProduct(String image, Product product) {
        this.image = image;
        this.product = product;
    }
}
