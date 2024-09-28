package com.example.NguyenVanNhat.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity( name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int price;
    private int discountprice;
    private Date expired;
    private int view;

    @ManyToMany(mappedBy = "products")
    private List<Style> styles = new ArrayList<>();
    @ManyToMany(mappedBy = "products")
    private List<Size> sizes = new ArrayList<>();
    @ManyToMany(mappedBy = "products")
    private List<Color> colors = new ArrayList<>();

    @ManyToMany(mappedBy = "products")
    private List<Categories> categories = new ArrayList<>();

    public Product(String name, String description, int price, int discountprice, Date expired, int view ) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.discountprice = discountprice;
        this.expired = expired;
        this.view = view;
    }
}
