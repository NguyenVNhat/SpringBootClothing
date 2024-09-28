package com.example.NguyenVanNhat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "rate_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RateProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int rate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public RateProduct(int rate, Product product) {
        this.rate = rate;
        this.product = product;
    }
}
