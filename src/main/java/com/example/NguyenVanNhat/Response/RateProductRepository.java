package com.example.NguyenVanNhat.Response;

import com.example.NguyenVanNhat.Models.RateProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateProductRepository extends JpaRepository<RateProduct, Integer> {
    List<RateProduct> findByproduct_id(int productId);
}
