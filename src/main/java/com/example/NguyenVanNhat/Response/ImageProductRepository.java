package com.example.NguyenVanNhat.Response;

import com.example.NguyenVanNhat.Models.ImageProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
    List<ImageProduct> findByproduct_id(int productId);
    ImageProduct findByid(int id);
}
